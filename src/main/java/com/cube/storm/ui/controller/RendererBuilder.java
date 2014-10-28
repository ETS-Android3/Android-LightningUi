package com.cube.storm.ui.controller;

/**
 * Builds renderers for the player.
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public interface RendererBuilder
{

	void buildRenderers(RendererBuilderCallback callback);

}
