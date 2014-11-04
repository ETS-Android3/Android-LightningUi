/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cube.storm.ui.lib.parser;

import android.media.MediaCodec;
import android.net.Uri;

import com.cube.storm.ui.activity.VideoPlayerActivity;
import com.cube.storm.ui.controller.ExoMediaPlayer;
import com.cube.storm.ui.controller.ExoMediaPlayer.RendererBuilder;
import com.cube.storm.ui.controller.ExoMediaPlayer.RendererBuilderCallback;
import com.google.android.exoplayer.FrameworkSampleSource;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.TrackRenderer;

public class DefaultRendererBuilder implements RendererBuilder
{

	private final VideoPlayerActivity playerActivity;
	private final Uri uri;

	public DefaultRendererBuilder(VideoPlayerActivity playerActivity, Uri uri)
	{
		this.playerActivity = playerActivity;
		this.uri = uri;
	}

	@Override
	public void buildRenderers(ExoMediaPlayer player, RendererBuilderCallback callback)
	{
		// Build the video and audio renderers.
		FrameworkSampleSource sampleSource = new FrameworkSampleSource(playerActivity, uri, null, 2);
		MediaCodecVideoTrackRenderer videoRenderer = new MediaCodecVideoTrackRenderer(sampleSource, null, true, MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT, 5000, player.getMainHandler(), player, 50);
		MediaCodecAudioTrackRenderer audioRenderer = new MediaCodecAudioTrackRenderer(sampleSource, null, true, player.getMainHandler(), player);

		// Build the debug renderer.
		TrackRenderer debugRenderer = null;

		// Invoke the callback.
		TrackRenderer[] renderers = new TrackRenderer[ExoMediaPlayer.RENDERER_COUNT];
		renderers[ExoMediaPlayer.TYPE_VIDEO] = videoRenderer;
		renderers[ExoMediaPlayer.TYPE_AUDIO] = audioRenderer;
		renderers[ExoMediaPlayer.TYPE_DEBUG] = debugRenderer;
		callback.onRenderers(null, null, renderers);
	}
}
