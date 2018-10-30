package com.cube.storm.ui.lib.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.lib.EventHook;
import com.cube.storm.ui.model.list.SpotlightListItem;
import com.cube.storm.ui.model.property.SpotlightImageProperty;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.TextView;

public class StormSpotlightAdapter extends PagerAdapter
{
	private SpotlightListItem spotlightListItem;

	public void setSpotlightListItem(@Nullable SpotlightListItem spotlightListItem)
	{
		if (this.spotlightListItem != spotlightListItem)
		{
			this.spotlightListItem = spotlightListItem;
			notifyDataSetChanged();
		}
	}

	@Override
	public int getCount()
	{
		if (spotlightListItem == null || spotlightListItem.getSpotlights() == null)
		{
			return 0;
		}
		return spotlightListItem.getSpotlights().size();
	}

	@Nullable
	public SpotlightImageProperty getItem(int position)
	{
		if (spotlightListItem != null
		    && spotlightListItem.getSpotlights() != null
		    && position >= 0
		    && spotlightListItem.getSpotlights().size() > position)
		{
			return spotlightListItem.getSpotlights().get(position);
		}

		return null;
	}

	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
	{
		return view == object;
	}

	@Override
	@NonNull
	public Object instantiateItem(@NonNull ViewGroup container, int position)
	{
		Context context = container.getContext();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.spotlight_image_view, null);

		final SpotlightImageProperty spotlightItem = getItem(position);
		if (spotlightItem != null)
		{
			ImageView imageView = view.findViewById(R.id.image_view);
			TextView textView = view.findViewById(R.id.text_ticker);
			imageView.populate(spotlightItem.getImage());
			textView.populate(spotlightItem.getText(), spotlightItem.getLink());

			view.setOnClickListener(new View.OnClickListener()
			{
				@Override public void onClick(View v)
				{
					UiSettings.getInstance().getLinkHandler().handleLink(v.getContext(), spotlightItem.getLink());

					for (EventHook eventHook : UiSettings.getInstance().getEventHooks())
					{
						eventHook.onViewLinkedClicked(v, spotlightListItem, spotlightItem.getLink());
					}
				}
			});
		}

		ViewPager viewPager = (ViewPager) container;
		viewPager.addView(view, 0);

		return view;
	}

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
	{
		ViewPager viewPager = (ViewPager) container;
		View view = (View) object;
		viewPager.removeView(view);
	}
}
