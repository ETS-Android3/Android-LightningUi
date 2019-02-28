package com.cube.storm.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;
import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.lib.handler.LinkHandler;
import com.cube.storm.ui.model.property.VideoProperty;
import com.cube.storm.util.lib.resolver.Resolver;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.Arrays;
import java.util.List;

/**
 * Video player used to play videos from assets/file/http URI streams.
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class VideoPlayerActivity extends Activity implements PlaybackPreparer
{
	public static final String EXTRA_VIDEO = "extra_video";

	/**
	 * Priority list of Youtube itag formats to attempt to retrieve
	 * <p>
	 * See http://en.wikipedia.org/wiki/YouTube#Quality_and_formats
	 * <p>
	 * TODO: In future can add DASH media sources
	 */
	private static final List<Integer> YOUTUBE_ITAG_PREFERENCE = Arrays.asList(22, 18, 43, 5, 36, 17);

	// Saved instance state keys.
	private static final String KEY_WINDOW = "window";
	private static final String KEY_POSITION = "position";
	private static final String KEY_AUTO_PLAY = "auto_play";
	private static final String KEY_URI = "playing_uri";

	private PlayerView playerView;
	private ProgressBar progressBar;

	private DataSource.Factory dataSourceFactory;
	private SimpleExoPlayer player;
	private MediaSource mediaSource;

	private Uri uri;
	private boolean startAutoPlay;
	private int startWindow;
	private long startPosition;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "storm-video-player"));
		setContentView(R.layout.activity_video_player);

		playerView = findViewById(R.id.player_view);
		progressBar = findViewById(R.id.progress);
		playerView.requestFocus();

		if (savedInstanceState != null)
		{
			startAutoPlay = savedInstanceState.getBoolean(KEY_AUTO_PLAY);
			startWindow = savedInstanceState.getInt(KEY_WINDOW);
			startPosition = savedInstanceState.getLong(KEY_POSITION);
			String uriString = savedInstanceState.getString(KEY_URI);
			uri = uriString != null ? Uri.parse(uriString) : null;
		}
		else
		{
			clearStartPosition();
		}
	}

	@Override
	public void onStart()
	{
		super.onStart();
		if (Util.SDK_INT > 23)
		{
			initializePlayer();
			if (playerView != null)
			{
				playerView.onResume();
			}
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();
		if (Util.SDK_INT <= 23 || player == null)
		{
			initializePlayer();
			if (playerView != null)
			{
				playerView.onResume();
			}
		}
	}

	@Override
	public void onPause()
	{
		super.onPause();
		if (Util.SDK_INT <= 23)
		{
			if (playerView != null)
			{
				playerView.onPause();
			}
			releasePlayer();
		}
	}

	@Override
	public void onStop()
	{
		super.onStop();
		if (Util.SDK_INT > 23)
		{
			if (playerView != null)
			{
				playerView.onPause();
			}
			releasePlayer();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		updateStartPosition();
		outState.putBoolean(KEY_AUTO_PLAY, startAutoPlay);
		outState.putInt(KEY_WINDOW, startWindow);
		outState.putLong(KEY_POSITION, startPosition);
		outState.putString(KEY_URI, uri != null ? uri.toString() : "");
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event)
	{
		// See whether the player view wants to handle media or DPAD keys events.
		return playerView.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
	}

	@Override
	public void preparePlayback()
	{
		initializePlayer();
	}

	private void initializePlayer()
	{
		if (player == null)
		{
			player = ExoPlayerFactory.newSimpleInstance(this);
			player.setPlayWhenReady(startAutoPlay);
			playerView.setPlayer(player);
			playerView.setUseController(true);
			playerView.setPlaybackPreparer(this);

			if (this.uri == null && getIntent().hasExtra(EXTRA_VIDEO) && getIntent().getExtras().getSerializable(EXTRA_VIDEO) != null)
			{
				VideoProperty videoProperty = (VideoProperty) getIntent().getSerializableExtra(EXTRA_VIDEO);
				this.uri = Uri.parse(videoProperty.getSrc().getDestination());
			}

			boolean isResolved = false;
			boolean isMediaSourceReady = false;

			// Recursively attempt to resolve a uri
			// This recursion is to support Storm uri resolvers - usually we will only iterate once
			while (uri != null && uri.getScheme() != null && !isResolved)
			{
				switch (uri.getScheme())
				{
					case "assets":
					{
						uri = Uri.parse(uri.toString().replace("assets://", "asset:///"));
						isResolved = true;
						isMediaSourceReady = true;
						break;
					}
					case "file":
					{
						isResolved = true;
						isMediaSourceReady = true;
						break;
					}
					case "http":
					case "https":
					{
						isResolved = true;
						if (LinkHandler.isYoutubeVideo(uri))
						{
							isMediaSourceReady = false;
							extractRawYoutubeUri();
						}
						else
						{
							isMediaSourceReady = true;
						}
						break;
					}
					default:
					{
						Resolver resolver = UiSettings.getInstance().getUriResolvers().get(uri.getScheme());
						if (resolver != null)
						{
							String scheme = uri.getScheme();
							uri = resolver.resolveUri(uri);
							if (uri != null && scheme.equals(uri.getScheme()))
							{
								// avoid infinite recursion
								isResolved = true;
								isMediaSourceReady = true;
							}
						}
						else
						{
							// We're not sure what the uri is but may as well try it anyway
							isResolved = true;
							isMediaSourceReady = true;
						}
						break;
					}
				}
			}

			if (isMediaSourceReady)
			{
				initialiseMediaSource();
			}
		}
	}

	@SuppressLint("StaticFieldLeak")
	private void extractRawYoutubeUri()
	{
		new YouTubeExtractor(this)
		{
			@Override
			public void onExtractionComplete(
				SparseArray<YtFile> ytFiles,
				VideoMeta vMeta
			)
			{
				if (ytFiles != null)
				{
					for (Integer itag : YOUTUBE_ITAG_PREFERENCE)
					{
						YtFile file = ytFiles.get(itag);
						if (file != null)
						{
							VideoPlayerActivity.this.uri = Uri.parse(ytFiles.get(itag).getUrl());
							break;
						}
					}
					initialiseMediaSource();
				}
			}
		}.extract(uri.toString(), true, true);
	}

	private void initialiseMediaSource()
	{
		progressBar.setVisibility(View.GONE);

		if (uri != null)
		{
			mediaSource = buildMediaSource(uri);
		}
		else
		{
			Toast.makeText(this, "Could not load video.", Toast.LENGTH_LONG).show();
			finish();
		}

		boolean haveStartPosition = startWindow != C.INDEX_UNSET;
		if (haveStartPosition)
		{
			player.seekTo(startWindow, startPosition);
		}
		player.prepare(mediaSource, !haveStartPosition, false);
	}

	private MediaSource buildMediaSource(Uri uri)
	{
		@C.ContentType int type = Util.inferContentType(uri);
		switch (type)
		{
			case C.TYPE_DASH:
				return new DashMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
			case C.TYPE_SS:
				return new SsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
			case C.TYPE_HLS:
				return new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
			case C.TYPE_OTHER:
				return new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
			default:
			{
				throw new IllegalStateException("Unsupported type: " + type);
			}
		}
	}

	private void releasePlayer()
	{
		if (player != null)
		{
			updateStartPosition();
			player.release();
			player = null;
			mediaSource = null;
		}
	}

	private void updateStartPosition()
	{
		if (player != null)
		{
			startAutoPlay = player.getPlayWhenReady();
			startWindow = player.getCurrentWindowIndex();
			startPosition = Math.max(0, player.getContentPosition());
		}
	}

	private void clearStartPosition()
	{
		startAutoPlay = true;
		startWindow = C.INDEX_UNSET;
		startPosition = C.TIME_UNSET;
	}
}
