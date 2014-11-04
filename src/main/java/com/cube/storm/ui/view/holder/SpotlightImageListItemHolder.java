package com.cube.storm.ui.view.holder;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.SpotlightImageListItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Holder for populating the Spotlight image at the top of a list view.
 * The image will cycle behind the text and also update the text fluidly with it.
 *
 * The animation will be triggered by a {@link java.util.Timer} thread that calls back with a
 * {@link java.util.TimerTask} that will use the delay set for the property and then be removed and
 * re-set each time the view is updated, which will allow for inconsistent delay timings.
 *
 * This means that timings are not based on the callback from the ViewPropertyAnimator but also being
 * managed asynchronously.
 *
 * @author Matt Allen
 * @project Storm
 */
public class SpotlightImageListItemHolder extends Holder<SpotlightImageListItem>
{
	private static final int MSG_UPDATE = 100;
	private ImageView image;
	private TextView text;

	private Timer timer;
	private Handler handler;
	private SpotlightImageListItem model;
	private int currentIndex = 0;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spotlight_image_list_item_view, parent, false);
		image = (ImageView)view.findViewById(R.id.image_view);
		image.setTag(getTimer());
		text = (TextView)view.findViewById(R.id.text_ticker);
		return view;
	}

	@Override public void populateView(SpotlightImageListItem model)
	{
		if (this.model == null)
		{
			this.model = model;

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

			text.setText(model.getImages().get(currentIndex).getText().getContent());
			
			currentIndex++;
			if (currentIndex >= model.getImages().size())
			{
				currentIndex = 0;
			}
			timer.schedule(new TimerTask()
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
				timer = new Timer("Spotlight timer");
			}
		}
		return timer;
	}
}
