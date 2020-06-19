package com.cube.storm.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.page.TabbedPageCollection;
import com.cube.storm.ui.view.PagerSlidingTabStrip;
import lombok.Getter;

/**
 * Renders a Storm TabbedPageCollection as a top tabs view.
 *
 * This is a legacy class - top tabs aren't recommended for accessibility reasons
 */
@Deprecated
public class StormTopTabsFragment extends StormTabbedFragment
{
	@Getter protected PagerSlidingTabStrip indicator;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = super.onCreateView(inflater, container, savedInstanceState);
		indicator = view.findViewById(R.id.indicator);
		return view;
	}

	@Override
	protected void loadPages(@NonNull TabbedPageCollection collection)
	{
		super.loadPages(collection);
		indicator.setViewPager(viewPager);
	}

	@Override
	public int getLayoutResource()
	{
		return R.layout.tabbed_page_top_fragment_view;
	}
}
