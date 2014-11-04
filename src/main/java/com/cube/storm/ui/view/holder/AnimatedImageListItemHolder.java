package com.cube.storm.ui.view.holder;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.AnimatedImageListItem;

import java.io.FileNotFoundException;
import java.io.InputStream;
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
	ImageView image;
	AnimatedImageListItem model;//this is bad mmkay, controllers will come with the recycler view
	int currentIndex = 0;
	Timer timer;
	public static final int MSG_UPDATE = 1;


	@Override public View createView(ViewGroup parent)
	{

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animated_image_list_item_view, parent, false);
		image = (ImageView)view.findViewById(R.id.image_view);

		return view;
	}

	@Override public void populateView(AnimatedImageListItem model)
	{
		//resets view properties for reuse
		getTimer().cancel();
		currentIndex = 0;
		this.model = model;

		//check if images exist, use a defult image if not
		if(model.getImages() != null)
		{
			newImage(0);
		}
		else
		{
			//TODO insert default image
		}
	}

	/**
	 * New Image
	 * Choose the new image based on the index
	 * @param delay
	 */
	private void newImage(long delay)
	{
		//set up a new timer
		getTimer().schedule(new TimerTask()
		{
			@Override public void run()
			{
				try
				{
					Uri imageSrc = Uri.parse(model.getImages().get(currentIndex).getSrc());
					InputStream is = image.getContext().getContentResolver().openInputStream(imageSrc);
					image.setImageDrawable(Drawable.createFromStream(is, imageSrc.toString()));
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
				currentIndex++;

				if(currentIndex > model.getImages().size())
				{
					currentIndex = 0;
				}

				handler.sendEmptyMessage(MSG_UPDATE);
			}
		}, delay);
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

	private final Handler handler = new Handler()
	{

		public void handleMessage(Message msg)
		{
			if(msg.what == MSG_UPDATE)
			{
				newImage(model.getImages().get(currentIndex).getDelay());
			}
		}
	};
}
