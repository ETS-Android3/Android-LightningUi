package com.cube.storm.ui.controller;

import com.cube.storm.ui.activity.VideoPlayerActivity;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public final class RendererBuilderCallback
{
	VideoPlayerActivity videoPlayerActivity;

	public RendererBuilderCallback(VideoPlayerActivity videoPlayerActivity)
	{
		this.videoPlayerActivity = videoPlayerActivity;
	}

	public void onRenderers(MediaCodecVideoTrackRenderer videoRenderer, MediaCodecAudioTrackRenderer audioRenderer)
	{
		videoPlayerActivity.onRenderers(this, videoRenderer, audioRenderer);
	}

	public void onRenderersError(Exception e)
	{
		videoPlayerActivity.videoFailed();
	}
}
