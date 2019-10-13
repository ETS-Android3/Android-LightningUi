package com.cube.storm.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.activity.StormInterface;
import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.data.FragmentPackage;
import com.cube.storm.ui.lib.adapter.StormPageAdapter;
import com.cube.storm.ui.model.descriptor.TabbedPageDescriptor;
import com.cube.storm.ui.model.page.GridPage;
import com.cube.storm.ui.model.page.ListPage;
import com.cube.storm.ui.model.page.TabbedPageCollection;
import com.cube.storm.ui.view.PagerSlidingTabStrip;

import java.util.ArrayList;

import lombok.Getter;

/**
 * Base storm fragment that hosts a collection of {@link ListPage} or {@link GridPage}
 *
 * @author Callum Taylor
 * @project LightingUi
 */
public class StormTabbedFragment extends Fragment implements StormInterface
{
	@Getter protected StormPageAdapter pageAdapter;
	@Getter protected ViewPager viewPager;
	@Getter protected PagerSlidingTabStrip indicator;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(getLayoutResource(), container, false);
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
			loadPage(pageUri);
		}
		else
		{
			onLoadFail();
		}
	}

	protected void loadPages(@NonNull TabbedPageCollection collection)
	{
		pageAdapter = new StormPageAdapter(getActivity(), getChildFragmentManager());
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

		pageAdapter.setPages(fragmentPages);
		viewPager.setAdapter(pageAdapter);
		indicator.setViewPager(viewPager);

		viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
		{
			@Override public void onPageSelected(int position)
			{
				super.onPageSelected(position);

				try
				{
					Fragment fragment = getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + position);

					if (fragment instanceof StormFragment)
					{
						((StormFragment)fragment).onPageOpened();
					}
					else if (fragment instanceof StormStaticFragment)
					{
						((StormStaticFragment)fragment).onPageOpened();
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	@Override public int getLayoutResource()
	{
		return R.layout.tabbed_page_fragment_view;
	}

	@Override public void loadPage(String pageUri)
	{
		TabbedPageCollection pages = UiSettings.getInstance().getViewBuilder().buildTabbedPage(Uri.parse(pageUri));

		if (pages != null)
		{
			loadPages(pages);
		}
		else
		{
			onLoadFail();
		}
	}

	@Override public void onLoadFail()
	{
		Toast.makeText(getActivity(), "Failed to load page", Toast.LENGTH_SHORT).show();
		getActivity().finish();
	}
}
