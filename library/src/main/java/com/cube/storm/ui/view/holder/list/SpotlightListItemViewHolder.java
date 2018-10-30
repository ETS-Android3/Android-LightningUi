package com.cube.storm.ui.view.holder.list;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cube.storm.ui.R;
import com.cube.storm.ui.lib.adapter.StormSpotlightAdapter;
import com.cube.storm.ui.model.list.SpotlightListItem;
import com.cube.storm.ui.model.property.SpotlightImageProperty;
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

	protected ViewPager viewPager;
	protected TabLayout indicator;
	protected StormSpotlightAdapter spotlightAdapter = new StormSpotlightAdapter();
	private Runnable viewPagerTransition = new Runnable()
	{
		@Override
		public void run()
		{
			if (viewPager.getCurrentItem() < spotlightAdapter.getCount() - 1)
			{
				viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
			}
			else
			{
				viewPager.setCurrentItem(0);
			}
		}
	};

	public SpotlightListItemViewHolder(View view)
	{
		super(view);
		viewPager = view.findViewById(R.id.viewPager);
		indicator = view.findViewById(R.id.indicator);
		viewPager.setAdapter(spotlightAdapter);
		indicator.setupWithViewPager(viewPager, true);
		viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				scheduleNextTransition();
			}
		});
	}

	@Override public void populateView(final SpotlightListItem model)
	{
		spotlightAdapter.setSpotlightListItem(model);
		scheduleNextTransition();

		if (spotlightAdapter.getCount() <= 1)
		{
			indicator.setVisibility(View.GONE);
		}
		else
		{
			indicator.setVisibility(View.VISIBLE);
		}
	}

	private void scheduleNextTransition()
	{
		int currentIndex = viewPager.getCurrentItem();
		SpotlightImageProperty currentItem = spotlightAdapter.getItem(currentIndex);

		if (currentItem == null || spotlightAdapter.getCount() <= 1)
		{
			return;
		}

		viewPager.removeCallbacks(viewPagerTransition);
		viewPager.postDelayed(viewPagerTransition, currentItem.getDelay());
	}
}
