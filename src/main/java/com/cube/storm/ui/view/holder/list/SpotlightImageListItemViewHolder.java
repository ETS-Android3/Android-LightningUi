package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.SpotlightImageListItem;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

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
public class SpotlightImageListItemViewHolder extends ViewHolder<SpotlightImageListItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public SpotlightImageListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spotlight_image_list_item_view, parent, false);
			return new SpotlightImageListItemViewHolder(view);
		}
	}

	private ImageView image;
	private TextView text;
	private SpotlightImageListItem model;

	public SpotlightImageListItemViewHolder(View view)
	{
		super(view);
		image = (ImageView)view.findViewById(R.id.image_view);
		text = (TextView)view.findViewById(R.id.text_ticker);
	}

	@Override public void populateView(SpotlightImageListItem model)
	{
		// Only restart the animation if it is from a different model
		if (this.model != model)
		{
			this.model = model;
			image.populate(model.getImages(), text);
		}
	}
}
