package com.cube.storm.ui.view.holder.list;

import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cube.storm.ui.R;
import com.cube.storm.ui.lib.adapter.StormSpotlightAdapter;
import com.cube.storm.ui.model.list.SpotlightListItem;
import com.cube.storm.ui.view.WrapContentViewPager;
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
public class SpotlightListItemViewHolder extends ViewHolder<SpotlightListItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public SpotlightListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spotlight_image_list_item_view, parent, false);
			return new SpotlightListItemViewHolder(view);
		}
	}

	protected WrapContentViewPager viewPager;
	protected TabLayout indicator;
	protected StormSpotlightAdapter spotlightAdapter = new StormSpotlightAdapter();

	public SpotlightListItemViewHolder(View view)
	{
		super(view);
		viewPager = view.findViewById(R.id.viewPager);

		indicator = view.findViewById(R.id.indicator);
		viewPager.setAdapter(spotlightAdapter);
		viewPager.setClipToPadding(false);
		indicator.setupWithViewPager(viewPager, true);
	}

	@Override public void populateView(final SpotlightListItem model)
	{
		spotlightAdapter.setSpotlightListItem(model);

		if (spotlightAdapter.getCount() <= 1)
		{
			indicator.setVisibility(View.GONE);
		}
		else
		{
			indicator.setVisibility(View.VISIBLE);
		}
		/**
		 * Need to set the number of the items, so the {@link WrapContentViewPager }
		 * will calculate the highest height and set for all the views the same height.
		 * Otherwise, each view will have different height
		 */
		viewPager.setOffscreenPageLimit(spotlightAdapter.getCount());


		updateSpotlightIndicatorContentDescriptions();
		indicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
		{
			@Override public void onTabSelected(TabLayout.Tab tab)
			{
				updateSpotlightIndicatorContentDescriptions();
			}

			@Override public void onTabUnselected(TabLayout.Tab tab)
			{
			}

			@Override public void onTabReselected(TabLayout.Tab tab)
			{
			}
		});
	}

	protected void updateSpotlightIndicatorContentDescriptions()
	{
		int count = indicator.getTabCount();
		for (int index = 0; index < count; index++)
		{
			// "selected" is already added to content description.
			String description = "tab " + index + " of " + count;
			indicator.getTabAt(index).setContentDescription(description);
		}
	}
}
