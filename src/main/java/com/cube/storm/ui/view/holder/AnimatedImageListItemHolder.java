package com.cube.storm.ui.view.holder;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.AnimatedImageListItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Timer;
import java.util.TimerTask;

/**
 * // TODO: Add class description
 *
 * @author Luke Reed
 * @project Storm
 */
public class AnimatedImageListItemHolder extends Holder<AnimatedImageListItem>
{
	private static final int MSG_UPDATE = 1;

	private ImageView image;
	private AnimatedImageListItem model;//this is bad mmkay, controllers will come with the recycler view
	private int currentIndex = 0;
	private Timer timer;
	private Handler handler;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animated_image_list_item_view, parent, false);
		image = (ImageView)view.findViewById(R.id.image_view);

		return view;
	}

	@Override public void populateView(AnimatedImageListItem model)
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
		if (model.getImages() != null)
		{
			if (currentIndex >= model.getImages().size())
			{
				currentIndex = 0;
			}

			ImageLoader.getInstance().displayImage(model.getImages().get(currentIndex).getSrc(), image);

			currentIndex++;
			if (currentIndex >= model.getImages().size())
			{
				currentIndex = 0;
			}
			getTimer().schedule(new TimerTask()
			{
				@Override public void run()
				{
					handler.sendEmptyMessage(MSG_UPDATE);
				}
			}, model.getImages().get(currentIndex).getDelay());
		}
	}

	protected Timer getTimer()
	{
		if (timer == null)
		{
			if (image.getTag() != null && image.getTag() instanceof Timer)
			{
				timer = (Timer) image.getTag();
			}
			else
			{
				timer = new Timer("Animated Image List Item timer");
			}
		}
		return timer;
	}
}
