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

import com.cube.storm.ui.R;
import com.cube.storm.ui.controller.DemoPlayer;
import com.cube.storm.ui.controller.DemoPlayer.RendererBuilder;
import com.cube.storm.ui.lib.helper.YouTubeHelper;
import com.cube.storm.ui.lib.parser.DefaultRendererBuilder;
import com.cube.storm.ui.model.property.VideoProperty;
import com.cube.storm.ui.view.VideoControllerView;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.VideoSurfaceView;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class VideoPlayerActivity extends Activity implements SurfaceHolder.Callback, DemoPlayer.Listener
{
	public static final String EXTRA_VIDEOS = "extra_videos";
	public static final String EXTRA_FILE_NAME = "extra_file_name";

	private DemoPlayer player;

	private VideoControllerView videoControllerView;
	private View shutterView;
	private VideoSurfaceView surfaceView;
	private Spinner videoChooser;

	private Uri contentUri;

	private boolean autoPlay = true;
	private int playerPosition;
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

		contentUri = getIntent().getData();

		videoControllerView = new VideoControllerView(this);
		videoControllerView.setAnchorView((FrameLayout)findViewById(R.id.root));

		surfaceView.getHolder().addCallback(this);
		Bundle bundle = getIntent().getExtras();
		fileName = bundle.getString(EXTRA_FILE_NAME);

		if (getIntent().hasExtra(EXTRA_VIDEOS) && bundle.getSerializable(EXTRA_VIDEOS) != null)
		{
			Object[] array = (Object[])bundle.getSerializable(EXTRA_VIDEOS);
			otherVideos = new VideoProperty[array.length];

			for (int index = 0; index < otherVideos.length; index++)
			{
				otherVideos[index] = (VideoProperty)array[index];
			}

			if (otherVideos != null && otherVideos.length > 1)
			{
				String[] locales = new String[otherVideos.length];
				int selectedIndex = 0;

				for (int index = 0; index < locales.length; index++)
				{

					locales[index] = "";

					if (otherVideos[index].getDestination().getDestination().equals(fileName))
					{
						selectedIndex = index;
					}
				}

				ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, locales);

				videoChooser.setVisibility(View.VISIBLE);
				videoChooser.setAdapter(adapter);
				videoChooser.setSelection(selectedIndex);
				videoChooser.setOnItemSelectedListener(new OnItemSelectedListener()
				{
					@Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
					{
						if (playCount++ > 0)
						{
							System.out.println(otherVideos[position].getDestination().getDestination());
							contentUri = Uri.parse(otherVideos[position].getDestination().getDestination());
							autoPlay = true;
							player.release();
							player = null;
							preparePlayer();
						}
					}

					@Override public void onNothingSelected(AdapterView<?> parent)
					{
					}
				});
			}
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
				player = new DemoPlayer(builder);
				player.addListener(this);
				player.seekTo(playerPosition);

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
			player = new DemoPlayer(rendererBuilder);
			player.addListener(this);
			player.seekTo(playerPosition);

			playerNeedsPrepare = true;
			videoControllerView.setMediaPlayer(player.getPlayerControl());
			videoControllerView.setEnabled(true);
		}

		if (playerNeedsPrepare)
		{
			player.prepare();
			playerNeedsPrepare = false;
		}

		player.setSurface(surfaceView.getHolder().getSurface());
		maybeStartPlayback();
	}

	private void maybeStartPlayback()
	{
		if (autoPlay && (player.getSurface().isValid() || player.getSelectedTrackIndex(DemoPlayer.TYPE_VIDEO) == DemoPlayer.DISABLED_TRACK))
		{
			player.setPlayWhenReady(true);
			autoPlay = false;
		}
	}

	private RendererBuilder getRendererBuilder()
	{
		if (contentUri.getScheme().equals("assets"))
		{
			return new DefaultRendererBuilder(this, contentUri);
		}
		else if (isYoutubeVideo(contentUri))
		{
			YouTubeHelper.getStreamingUrl(contentUri.toString(), new YouTubeHelper.Callback()
			{
				@Override public void onStreamingUrlFetched(String streamingUrl)
				{
					contentUri = Uri.parse(streamingUrl);
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
			return null;
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

	private void showControls()
	{
		videoControllerView.show(0);
	}

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
			playerPosition = player.getCurrentPosition();
			player.release();
			player = null;
		}
	}

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
