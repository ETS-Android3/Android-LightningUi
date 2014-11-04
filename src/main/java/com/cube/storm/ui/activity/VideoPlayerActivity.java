package com.cube.storm.ui.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.view.player.ExoMediaPlayer;
import com.cube.storm.ui.view.player.ExoMediaPlayer.RendererBuilder;
import com.cube.storm.ui.lib.helper.YouTubeHelper;
import com.cube.storm.ui.lib.parser.DefaultRendererBuilder;
import com.cube.storm.ui.model.property.VideoProperty;
import com.cube.storm.ui.view.VideoControllerView;
import com.cube.storm.util.lib.resolver.Resolver;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.VideoSurfaceView;

import java.io.File;
import java.util.ArrayList;

/**
 * Video player used to play videos from assets/file/http URI streams.
 * <p/>
 * Can take either a single URI extra using the key {@link StormActivity#EXTRA_URI} or an array list of
 * {@link com.cube.storm.ui.model.property.VideoProperty} using the key {@link #EXTRA_VIDEOS}. The default
 * video that gets played is the one that matches the current locale of the device, based on the {@link com.cube.storm.ui.model.property.VideoProperty#getLocale()} property
 * of the model.
 *
 * @author Alan Le Fournis
 * @project StormUi
 */
public class VideoPlayerActivity extends Activity implements SurfaceHolder.Callback, ExoMediaPlayer.Listener
{
	public static final String EXTRA_VIDEOS = "extra_videos";
	public static final String EXTRA_FILE_NAME = "extra_file_name";
	public static final String SELECTED_ITEM = "selected_item";

	private ExoMediaPlayer player;

	private VideoControllerView videoControllerView;
	private View shutterView;
	private VideoSurfaceView surfaceView;
	private Spinner videoChooser;

	private Uri contentUri;

	private boolean autoPlay = true;
	private boolean playerNeedsPrepare;

	protected VideoProperty[] otherVideos;
	private int playCount = 0;
	private String fileName;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_player);

		videoChooser = (Spinner)findViewById(R.id.videos);
		View root = findViewById(R.id.root);
		shutterView = findViewById(R.id.shutter);
		surfaceView = (VideoSurfaceView) findViewById(R.id.surface_view);

		root.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View view, MotionEvent arg1)
			{
				if (arg1.getAction() == MotionEvent.ACTION_DOWN)
				{
					toggleControlsVisibility();
				}
				return true;
			}
		});

		videoControllerView = new VideoControllerView(this);
		videoControllerView.setAnchorView((FrameLayout)findViewById(R.id.root));

		surfaceView.getHolder().addCallback(this);
		Bundle bundle = getIntent().getExtras();
		fileName = bundle.getString(EXTRA_FILE_NAME);

		if (getIntent().hasExtra(EXTRA_VIDEOS) && bundle.getSerializable(EXTRA_VIDEOS) != null)
		{
			ArrayList<VideoProperty> array = (ArrayList<VideoProperty>)bundle.getSerializable(EXTRA_VIDEOS);
			otherVideos = new VideoProperty[array.size()];

			for (int index = 0; index < otherVideos.length; index++)
			{
				otherVideos[index] = array.get(index);
			}

			if (otherVideos != null && otherVideos.length > 0)
			{
				String[] locales = new String[otherVideos.length];
				int selectedIndex = -1;

				for (int index = 0; index < locales.length; index++)
				{
					String languageSuffix = otherVideos[index].getLocale();

					if (languageSuffix.toLowerCase().contains("he"))
					{
						languageSuffix = languageSuffix.replace("he", "iw");
					}
					else if (languageSuffix.toLowerCase().contains("id"))
					{
						languageSuffix = languageSuffix.replace("id", "in");
					}
					else if (languageSuffix.toLowerCase().contains("yi"))
					{
						languageSuffix = languageSuffix.replace("yi", "ji");
					}

					if (otherVideos[index].getLocale().equals(fileName))
					{
						selectedIndex = index;
					}

					locales[index] = languageSuffix;
				}

				if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_ITEM))
				{
					selectedIndex = savedInstanceState.getInt(SELECTED_ITEM);
					contentUri = Uri.parse(otherVideos[selectedIndex].getSrc().getDestination());
				}
				else if (selectedIndex == -1)
				{
					selectedIndex = 0;
					contentUri = Uri.parse(otherVideos[0].getSrc().getDestination());
				}

				ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locales);

				videoChooser.setVisibility(View.VISIBLE);
				videoChooser.setAdapter(adapter);
				videoChooser.setSelection(selectedIndex);
				videoChooser.setOnItemSelectedListener(new OnItemSelectedListener()
				{
					@Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
					{
						if (playCount++ > 0)
						{
							contentUri = Uri.parse(otherVideos[position].getSrc().getDestination());
							autoPlay = true;

							if (player != null)
							{
								player.release();
								player = null;
							}

							preparePlayer();
						}
					}

					@Override public void onNothingSelected(AdapterView<?> parent)
					{
					}
				});
			}
		}
		else if (getIntent().hasExtra(StormActivity.EXTRA_URI) && bundle.getString(StormActivity.EXTRA_URI) != null)
		{
			videoChooser.setVisibility(View.GONE);
			contentUri = Uri.parse(bundle.getString(StormActivity.EXTRA_URI));
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();
		preparePlayer();
	}

	@Override protected void onPause()
	{
		super.onPause();
		releasePlayer();
	}

	@Override protected void onDestroy()
	{
		super.onDestroy();
		releasePlayer();
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		savedInstanceState.putInt(SELECTED_ITEM, videoChooser.getSelectedItemPosition());
		super.onSaveInstanceState(savedInstanceState);
	}

	/**
	 * Prepares the player by creating a renderer. If the renderer is null, then prevent the player from
	 * being created (possibly being streamed from an AsyncTask)
	 */
	private void preparePlayer()
	{
		if (player == null)
		{
			RendererBuilder builder = getRendererBuilder();

			if (builder == null)
			{
				return;
			}
			else
			{
				player = new ExoMediaPlayer(builder);
				player.addListener(this);

				playerNeedsPrepare = true;
				videoControllerView.setMediaPlayer(player.getPlayerControl());
				videoControllerView.setEnabled(true);
			}
		}

		if (playerNeedsPrepare)
		{
			player.prepare();
			playerNeedsPrepare = false;
		}

		player.setSurface(surfaceView.getHolder().getSurface());
		maybeStartPlayback();
	}

	private void prepareYoutubePlayer(RendererBuilder rendererBuilder)
	{
		if (player == null)
		{
			if (rendererBuilder == null)
			{
				return;
			}
			else
			{
				player = new ExoMediaPlayer(rendererBuilder);
				player.addListener(this);

				playerNeedsPrepare = true;
				videoControllerView.setMediaPlayer(player.getPlayerControl());
				videoControllerView.setEnabled(true);
			}
		}

		if (playerNeedsPrepare)
		{
			player.prepare();
			playerNeedsPrepare = false;
		}

		player.setSurface(surfaceView.getHolder().getSurface());
		maybeStartPlayback();
	}

	/**
	 * Start playback if player is configure and autoplay equals true
	 */
	private void maybeStartPlayback()
	{
		if (autoPlay && (player.getSurface().isValid() || player.getSelectedTrackIndex(ExoMediaPlayer.TYPE_VIDEO) == ExoMediaPlayer.DISABLED_TRACK))
		{
			player.setPlayWhenReady(true);
		}
	}

	/**
	 * Get the renderer builder according to the uri
	 *
	 * @return the renderer builder
	 */
	private RendererBuilder getRendererBuilder()
	{
		if (contentUri.getScheme().equals("assets"))
		{
			return new DefaultRendererBuilder(this, contentUri);
		}
		else if (contentUri.getScheme().equals("file"))
		{
			File f = new File(contentUri.getPath());

			if (!f.exists())
			{
				videoFailed();
				return null;
			}

			return new DefaultRendererBuilder(this, contentUri);
		}
		else if (isYoutubeVideo(contentUri))
		{
			YouTubeHelper.getStreamingUrl(contentUri.toString(), new YouTubeHelper.Callback()
			{
				@Override public void onStreamingUrlFetched(String streamingUrl)
				{
					prepareYoutubePlayer(new DefaultRendererBuilder(VideoPlayerActivity.this, Uri.parse(streamingUrl)));
				}

				@Override public void onFailed(String failMessage)
				{
					videoFailed();
				}
			});

			return null;
		}
		else
		{
			Resolver fallbackResolver = UiSettings.getInstance().getUriResolvers().get(contentUri.getScheme());

			if (fallbackResolver != null)
			{
				contentUri = fallbackResolver.resolveUri(contentUri);
				return getRendererBuilder();
			}
			else
			{
				videoFailed();
				return null;
			}
		}
	}

	public void videoFailed()
	{
		Toast.makeText(this, "Failed to load video", Toast.LENGTH_LONG).show();
		finish();
	}

	@Override
	public void onStateChanged(boolean playWhenReady, int playbackState)
	{
		if (playbackState == ExoPlayer.STATE_ENDED)
		{
			showControls();
		}
	}

	/**
	 * Show video player controls
	 */
	private void showControls()
	{
		videoControllerView.show(0);
	}

	/**
	 * Hide or show video player control
	 */
	private void toggleControlsVisibility()
	{
		if (videoControllerView.isShowing())
		{
			videoControllerView.hide();
		}
		else
		{
			videoControllerView.show(0);
		}
	}

	private void releasePlayer()
	{
		if (player != null)
		{
			player.release();
			player = null;
		}
	}

	/**
	 * boolean to know if it's a youtube link or not
	 *
	 * @param uri
	 * @return the boolean
	 */
	public boolean isYoutubeVideo(Uri uri)
	{
		return (uri.getHost().endsWith("youtube.com") && uri.getQueryParameter("v") != null) || (uri.getHost().endsWith("youtu.be") && uri.getPathSegments().size() > 0);
	}

	@Override public void surfaceCreated(SurfaceHolder holder)
	{
		if (player != null)
		{
			player.setSurface(holder.getSurface());
			maybeStartPlayback();
		}
	}

	@Override public void surfaceDestroyed(SurfaceHolder holder)
	{
		if (player != null)
		{
			player.blockingClearSurface();
		}
	}

	@Override public void onError(Exception e)
	{

	}

	@Override public void onVideoSizeChanged(int width, int height)
	{
		shutterView.setVisibility(View.GONE);
		surfaceView.setVideoWidthHeightRatio(height == 0 ? 1 : (float) width / height);
	}

	@Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{

	}
}
