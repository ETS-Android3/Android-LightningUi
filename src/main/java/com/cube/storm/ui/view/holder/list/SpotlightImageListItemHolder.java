package com.cube.storm.ui.view.holder.list;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.SpotlightImageListItem;
import com.cube.storm.ui.view.ViewClickable;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;

import java.util.Timer;

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
public class SpotlightImageListItemHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spotlight_image_list_item_view, parent, false);
		mViewHolder = new SpotlightImageListItemViewHolder(view);

		return mViewHolder;
	}

	public class SpotlightImageListItemViewHolder extends ViewHolder<SpotlightImageListItem> implements ViewClickable<SpotlightImageListItem>
	{
		private static final int MSG_UPDATE = 100;
		private ImageView image;
		private TextView text;

		private Timer timer;
		private Handler handler;
		private SpotlightImageListItem model;
		private int currentIndex = 0;

		public SpotlightImageListItemViewHolder(View view)
		{
			super(view);
			image = (ImageView)view.findViewById(R.id.image_view);
			image.setTag(getTimer());
			text = (TextView)view.findViewById(R.id.text_ticker);
		}

		@Override public void populateView(SpotlightImageListItem model)
		{
			if (this.model == null)
			{
				currentIndex = 0;

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
//				if (currentIndex >= model.getImages().size())
//				{
//					currentIndex = 0;
//				}
//
//				ImageLoader.getInstance().displayImage(model.getImages().get(currentIndex), image);
//
//				String content = UiSettings.getInstance().getTextProcessor().process(model.getImages().get(currentIndex).getText().getContent());
//
//				if (!TextUtils.isEmpty(content))
//				{
//					text.setText(content);
//					text.setVisibility(View.VISIBLE);
//				}
//				else
//				{
//					text.setVisibility(View.GONE);
//				}
//
//				currentIndex++;
//				if (currentIndex >= model.getImages().size())
//				{
//					currentIndex = 0;
//				}
//
//				timer.schedule(new TimerTask()
//				{
//					@Override public void run()
//					{
//						handler.sendEmptyMessage(MSG_UPDATE);
//					}
//				}, model.getImages().get(currentIndex).getDelay());
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
					timer = new Timer("Spotlight timer");
				}
			}

			return timer;
		}

		@Override public void onClick(@NonNull SpotlightImageListItem model, @NonNull View view)
		{
			// TODO Redo this with a standard OnClickListener interface
			if (model.getImages() != null && model.getImages().get(currentIndex).getLink() != null)
			{
				UiSettings.getInstance().getLinkHandler().handleLink(view.getContext(), model.getImages().get(currentIndex).getLink());
			}
		}
	}
}
