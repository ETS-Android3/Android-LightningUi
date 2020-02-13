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
	public int getItemPosition(@NonNull Object object)
	{
		if (spotlightListItem != null && spotlightListItem.getSpotlights() != null)
		{
			View view = (View)object;

			if (view.getTag() instanceof Integer)
			{
				Integer viewTag = (Integer) view.getTag();
				int idx = 0;
				for (SpotlightImageProperty imageProperty : spotlightListItem.getSpotlights())
				{
					if (imageProperty != null && imageProperty.hashCode() == viewTag)
					{
						return idx;
					}
					++idx;
				}
			}
		}

		return POSITION_NONE;
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
		View view = inflater.inflate(R.layout.spotlight_image_view, container, false);

		final SpotlightImageProperty spotlightItem = getItem(position);
		if (spotlightItem != null)
		{
			View textContainer = view.findViewById(R.id.text_container);
			ImageView imageView = view.findViewById(R.id.image_view);
			TextView category = view.findViewById(R.id.category);
			TextView title = view.findViewById(R.id.title);
			TextView description = view.findViewById(R.id.description);

			imageView.populate(spotlightItem.getImage(), spotlightItem.getAccessibilityLabel());
			category.populate(spotlightItem.getCategory());
			title.populate(spotlightItem.getTitle());
			description.populate(spotlightItem.getDescription());

			// Hide text container under spotlight image if no text to populate
			if (category.getVisibility() != View.VISIBLE &&
				title.getVisibility() != View.VISIBLE &&
				description.getVisibility() != View.VISIBLE)
			{
				textContainer.setVisibility(View.GONE);
			}
			else
			{
				textContainer.setVisibility(View.VISIBLE);
			}
			// Keep text aligned across all spotlights. This must be done after the visibility check above.
			category.setVisibility(View.VISIBLE);

			View.OnClickListener spotlightClickListener = new View.OnClickListener()
			{
				@Override public void onClick(View v)
				{
					UiSettings.getInstance().getLinkHandler().handleLink(v.getContext(), spotlightItem.getLink());

					for (EventHook eventHook : UiSettings.getInstance().getEventHooks())
					{
						eventHook.onViewLinkedClicked(v, spotlightListItem, spotlightItem.getLink());
					}
				}
			};
			// ARCEM-1464: text container and image both need to be announced with clickable role (for talkback users)
			imageView.setOnClickListener(spotlightClickListener);
			textContainer.setOnClickListener(spotlightClickListener);
		}

		// Set the item's hashcode as the view tag so we can identify the original item from the view
		// in #getItemPosition. The ViewPager uses this method to identify which views have changed after
		// notifyDataSetChanged is called.
		view.setTag(spotlightItem != null ? spotlightItem.hashCode() : 0);

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
