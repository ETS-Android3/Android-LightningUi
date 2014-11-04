package com.cube.storm.ui.view.holder;

import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.model.list.AnimatedImageListItem;

/**
 * // TODO: Add class description
 *
 * @author Luke Reed
 * @project Storm
 */
public class AnimatedImageListItemHolder extends Holder<AnimatedImageListItem>
{
	@Override public View createView(ViewGroup parent)
	{
		return new View(parent.getContext());
	}

	@Override public void populateView(AnimatedImageListItem model)
	{

	}
}
