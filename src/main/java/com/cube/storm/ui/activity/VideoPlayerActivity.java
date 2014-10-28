package com.cube.storm.ui.activity;

import android.app.Activity;
import android.media.MediaCodec.CryptoException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cube.storm.ui.R;
import com.cube.storm.ui.controller.RendererBuilder;
import com.cube.storm.ui.controller.RendererBuilderCallback;
import com.cube.storm.ui.lib.helper.YouTubeHelper;
import com.cube.storm.ui.lib.parser.DefaultRendererBuilder;
import com.cube.storm.ui.view.VideoControllerView;
import com.cube.storm.ui.view.VideoControllerView.OnControllerVisibilityChangeListener;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.ExoPlayerLibraryInfo;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecTrackRenderer.DecoderInitializationException;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.VideoSurfaceView;
import com.google.android.exoplayer.util.PlayerControl;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class VideoPlayerActivity extends Activity implements SurfaceHolder.Callback, ExoPlayer.Listener, MediaCodecVideoTrackRenderer.EventListener, OnControllerVisibilityChangeListener
{
	public static final String CURRENT_POSITION = "current_position";
	public static final String EXTRA_VIDEOS = "videos";

	private ExoPlayer player;
	private RendererBuilder builder;
	private MediaCodecVideoTrackRenderer videoRenderer;
	private RendererBuilderCallback callback;

	private VideoControllerView videoControllerView;
	private Handler mainHandler;
	private View shutterView;
	private VideoSurfaceView surfaceView;

	private Uri contentUri;

	private boolean autoPlay = true;
	private int playerPosition;
	private boolean playerNeedsPrepare;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_player);

		View root = findViewById(R.id.root);
		shutterView = findViewById(R.id.shutter);
		surfaceView = (VideoSurfaceView) findViewById(R.id.surface_view);

		root.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1)
			{
				if (arg1.getAction() == MotionEvent.ACTION_DOWN)
				{
					toggleControlsVisibility();
				}
				return true;
			}
		});

		contentUri = getIntent().getData();

		mainHandler = new Handler(getMainLooper());
		videoControllerView = new VideoControllerView(this);
		videoControllerView.setOnControllerVisibilityChangeListener(this);
		videoControllerView.setAnchorView((FrameLayout)findViewById(R.id.root));

		surfaceView.getHolder().addCallback(this);
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
		Log.e("preparePlayer", "prepare player");
		if (player == null)
		{
			player = ExoPlayer.Factory.newInstance(2, 1000, 5000);
			player.addListener(this);
			player.seekTo(playerPosition);
			playerNeedsPrepare = true;
			videoControllerView.setMediaPlayer(new PlayerControl(player));
			videoControllerView.setEnabled(true);

			//getRendererBuilder();
		}

		if (playerNeedsPrepare)
		{
			player.prepare();
			playerNeedsPrepare = false;
		}
		maybeStartPlayback();
	}

	private void getRendererBuilder()
	{
		Log.e("getRenderer", "getrendrer");

		if (contentUri.getScheme().equals("assets"))
		{
			loadPlayer(new DefaultRendererBuilder(this, contentUri));
		}
		else if (isYoutubeVideo(contentUri))
		{
			YouTubeHelper.getStreamingUrl(contentUri.toString(), new YouTubeHelper.Callback()
			{
				@Override public void onStreamingUrlFetched(String streamingUrl)
				{
					contentUri = Uri.parse(streamingUrl);
					loadPlayer(new DefaultRendererBuilder(VideoPlayerActivity.this, Uri.parse(streamingUrl)));
				}

				@Override public void onFailed(String failMessage)
				{
					videoFailed();
				}
			});
		}
	}

	private void loadPlayer(RendererBuilder rendererBuilder)
	{
		Log.e("loadPlayer", "LoadPlayer");

		try
		{
			builder = rendererBuilder;

			callback = new RendererBuilderCallback(this);
			builder.buildRenderers(callback);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			videoFailed();
		}
	}

	public void onRenderers(RendererBuilderCallback callback, MediaCodecVideoTrackRenderer videoRenderer, MediaCodecAudioTrackRenderer audioRenderer)
	{
		Log.e("onRenderes", "onRenderer");


		this.callback = null;
		this.videoRenderer = videoRenderer;
		player.prepare(videoRenderer, audioRenderer);
		maybeStartPlayback();
	}

	public void videoFailed()
	{
		Toast.makeText(this, "Failed to load video", Toast.LENGTH_LONG).show();
		finish();
	}

	private void maybeStartPlayback()
	{
		Log.e("maybeStartPlayback", "maybeStartPlayback");
		Log.e("maybeStartPlayback", "maybeStartPlayback " + autoPlay);

		Surface surface = surfaceView.getHolder().getSurface();

		player.sendMessage(videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, surface);
		player.setPlayWhenReady(true);
		if (autoPlay)
		{
			player.setPlayWhenReady(true);
			autoPlay = false;
			getRendererBuilder();

		}
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

	public static String getUserAgent()
	{
		String versionName = "?";

		return "ExoPlayerDemo/" + versionName + " (Linux;Android " + Build.VERSION.RELEASE + ") " + "ExoPlayerLib/" + ExoPlayerLibraryInfo.VERSION;
	}

	public Handler getMainHandler() {
		return mainHandler;
	}

	public boolean isYoutubeVideo(Uri uri)
	{
		return (uri.getHost().endsWith("youtube.com") && uri.getQueryParameter("v") != null) || (uri.getHost().endsWith("youtu.be") && uri.getPathSegments().size() > 0);
	}

	@Override public void surfaceCreated(SurfaceHolder holder)
	{
		maybeStartPlayback();
	}

	@Override public void surfaceDestroyed(SurfaceHolder holder)
	{

	}

	@Override public void onVideoSizeChanged(int width, int height)
	{
		surfaceView.setVideoWidthHeightRatio(height == 0 ? 1 : (float) width / height);
	}

	@Override public void onDrawnToSurface(Surface surface)
	{
		shutterView.setVisibility(View.GONE);
	}

	@Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{

	}

	@Override public void onDecoderInitializationError(DecoderInitializationException e)
	{

	}

	@Override public void onCryptoError(CryptoException e)
	{

	}

	@Override public void onPlayerStateChanged(boolean playWhenReady, int playbackState)
	{

	}

	@Override public void onPlayWhenReadyCommitted()
	{

	}

	@Override public void onPlayerError(ExoPlaybackException error)
	{

	}

	@Override public void onDroppedFrames(int count, long elapsed)
	{

	}

	@Override public void onHide()
	{

	}

	@Override public void onShow()
	{

	}
}
