package com.cube.storm.ui.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.data.FragmentPackage;
import com.cube.storm.ui.lib.adapter.StormPageAdapter;
import com.cube.storm.ui.model.descriptor.TabbedPageDescriptor;
import com.cube.storm.ui.model.page.TabbedPageCollection;
import com.cube.storm.util.lib.debug.Debug;

import java.util.ArrayList;

import lombok.Getter;

public class StormTabbedFragment extends Fragment
{
	@Getter protected StormPageAdapter pageAdapter;
	protected ViewPager viewPager;
	protected PagerSlidingTabStrip indicator;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.tabbed_page_fragment_view, container, false);
		viewPager = (ViewPager)view.findViewById(R.id.view_pager);
		indicator = (PagerSlidingTabStrip)view.findViewById(R.id.indicator);

		return view;
	}

	@Override public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		if (getArguments().containsKey(StormActivity.EXTRA_URI))
		{
			String pageUri = getArguments().getString(StormActivity.EXTRA_URI);
			TabbedPageCollection pages = UiSettings.getInstance().getViewBuilder().buildTabbedPage(Uri.parse(pageUri));

			if (pages != null)
			{
				loadPages(pages);
			}
		}
	}

	protected void loadPages(@NonNull TabbedPageCollection collection)
	{
		pageAdapter = new StormPageAdapter(getActivity(), getFragmentManager());
		ArrayList<FragmentPackage> fragmentPages = new ArrayList<FragmentPackage>();

		if (collection.getPages() != null)
		{
			for (TabbedPageDescriptor tabbedPageDescriptor : collection.getPages())
			{
				FragmentIntent fragmentIntent = UiSettings.getInstance().getIntentFactory().getFragmentIntentForPageDescriptor(tabbedPageDescriptor);

				if (fragmentIntent != null)
				{
					FragmentPackage fragmentPackage = new FragmentPackage(fragmentIntent, tabbedPageDescriptor);
					fragmentPages.add(fragmentPackage);
				}
			}
		}

		Debug.out(indicator);

		pageAdapter.setPages(fragmentPages);
		viewPager.setAdapter(pageAdapter);
		indicator.setViewPager(viewPager);
	}
}
