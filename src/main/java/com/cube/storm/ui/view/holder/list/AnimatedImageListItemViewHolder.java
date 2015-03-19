package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.AnimatedImageListItem;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * Animated image list item shows a series of items in turn. This is done by creating an asynchronous
 * {@link java.util.Timer} thread and creating a new {@link java.util.TimerTask} for each the next
 * image after each image is shown on screen.
 *
 * We're no longer relying on properties of a {@link android.graphics.drawable.AnimationDrawable}, this
 * is now asynchronous for performance reasons.
 *
 * The purpose of the Handler is to let us back onto the UI thread once the {@link java.util.TimerTask}
 * has returned.
 *
 * @author Luke Reed
 * @project Storm
 */
public class AnimatedImageListItemViewHolder extends ViewHolder<AnimatedImageListItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public AnimatedImageListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animated_image_list_item_view, parent, false);
			return new AnimatedImageListItemViewHolder(view);
		}
	}

	private ImageView image;
	private AnimatedImageListItem model;

	public AnimatedImageListItemViewHolder(View view)
	{
		super(view);
		image = (ImageView)view.findViewById(R.id.image_view);
	}

	@Override public void populateView(AnimatedImageListItem model)
	{
		// Only restart the animation if it is from a different model
		if (this.model != model)
		{
			this.model = model;
			image.populate(model.getImages());
		}
	}

}
