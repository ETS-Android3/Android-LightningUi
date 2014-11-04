package com.cube.storm.ui.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.SpotlightImageListItem;

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
	private ImageView image;
	private TextView text;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spotlight_image_list_item_view, parent, false);
		image = (ImageView)view.findViewById(R.id.image_view);
		text = (TextView)view.findViewById(R.id.text_ticker);
		return view;
	}

	@Override public void populateView(SpotlightImageListItem model)
	{
		//image.setImageBitmap(model.);
	}
}
