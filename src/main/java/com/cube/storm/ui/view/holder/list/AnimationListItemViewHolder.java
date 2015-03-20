package com.cube.storm.ui.view.holder.list;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cube.storm.ui.R;
import com.cube.storm.ui.lib.helper.ImageHelper;
import com.cube.storm.ui.model.list.AnimationListItem;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Animated image list item shows a series of items in turn. This is done by creating an asynchronous
 * {@link java.util.Timer} thread and creating a new {@link java.util.TimerTask} for each the next
 * image after each image is shown on screen.
 * <p/>
 * We're no longer relying on properties of a {@link android.graphics.drawable.AnimationDrawable}, this
 * is now asynchronous for performance reasons.
 * <p/>
 * The purpose of the Handler is to let us back onto the UI thread once the {@link java.util.TimerTask}
 * has returned.
 *
 * @author Luke Reed
 * @project LightningUi
 */
public class AnimationListItemViewHolder extends ViewHolder<AnimationListItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public AnimationListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animated_image_list_item_view, parent, false);
			return new AnimationListItemViewHolder(view);
		}
	}

	private static final int MSG_UPDATE = 1;

	private ImageView image;
	private AnimationListItem model; //This is bad mmkay, controllers will come with the recycler view
	private int currentIndex = 0;
	private Timer timer;
	private Handler handler;

	public AnimationListItemViewHolder(View view)
	{
		super(view);
		image = (ImageView)view.findViewById(R.id.image_view);
	}

	@Override public void populateView(AnimationListItem model)
	{
		if (this.model == null)
		{
			handler = new Handler()
			{
				@Override public void handleMessage(Message msg)
				{
					if (msg.what == MSG_UPDATE)
					{
						updateView();
					}
				}
			};
			this.model = model;
			currentIndex = 0;

			updateView();
		}
	}

	private void updateView()
	{
		if (model.getAnimation() != null)
		{
			if (currentIndex >= model.getAnimation().getFrames().size())
			{
				currentIndex = 0;
			}

			ImageLoader.getInstance().displayImage(ImageHelper.getImageSrc(model.getAnimation().getFrames().get(currentIndex).getImage()), image);

			currentIndex++;
			if (currentIndex >= model.getAnimation().getFrames().size())
			{
				currentIndex = 0;
			}

			getTimer().schedule(new TimerTask()
			{
				@Override public void run()
				{
					handler.sendEmptyMessage(MSG_UPDATE);
				}
			}, model.getAnimation().getFrames().get(currentIndex).getDelay());
		}
	}

	protected Timer getTimer()
	{
		if (timer == null)
		{
			if (image.getTag() != null && image.getTag() instanceof Timer)
			{
				timer = (Timer)image.getTag();
			}
			else
			{
				timer = new Timer("Animated Image List Item timer");
			}
		}

		return timer;
	}
}
