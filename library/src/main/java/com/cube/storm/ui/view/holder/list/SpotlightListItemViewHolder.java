package com.cube.storm.ui.view.holder.list;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.SpotlightListItem;
import com.cube.storm.ui.model.property.AnimationImageProperty;
import com.cube.storm.ui.model.property.SpotlightImageProperty;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

import java.util.concurrent.atomic.AtomicInteger;

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
 * @Project LightningUi
 */
public class SpotlightListItemViewHolder extends ViewHolder<SpotlightListItem> implements View.OnClickListener
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public SpotlightListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spotlight_image_list_item_view, parent, false);
			return new SpotlightListItemViewHolder(view);
		}
	}

	private ImageView image;
	private TextView text;
	private SpotlightListItem model;

	private AtomicInteger index = new AtomicInteger(0);

	public SpotlightListItemViewHolder(View view)
	{
		super(view);

		view.setOnClickListener(this);
		image = (ImageView)view.findViewById(R.id.image_view);
		text = (TextView)view.findViewById(R.id.text_ticker);
	}

	@Override public void populateView(SpotlightListItem model)
	{
		// Only restart the animation if it is from a different model
		if (this.model != model)
		{
			this.model = model;
			image.populate(model.getSpotlights(), new ImageView.OnAnimationFrameChangeListener()
			{
				@Override public void onAnimationFrameChange(ImageView imageView, int frameIndex, AnimationImageProperty frame)
				{
					index.set(frameIndex);

					SpotlightImageProperty spotlightFrame = (SpotlightImageProperty)frame.getFrames().get(frameIndex);
					text.populate(spotlightFrame.getText(), spotlightFrame.getLink());
				}
			});
		}
	}

	@Override public void onClick(@Nullable View view)
	{
		if (model.getSpotlights().get(index.get()) != null)
		{
			UiSettings.getInstance().getLinkHandler().handleLink(image.getContext(), model.getSpotlights().get(index.get()).getLink());
		}
	}
}
