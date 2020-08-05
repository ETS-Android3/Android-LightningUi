package com.cube.storm.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

import java.util.ArrayList;

import lombok.Getter;

/**
 * Base storm fragment that hosts a collection of {@link ListPage} or {@link GridPage}
 *
 * @author Callum Taylor
 * @project LightingUi
 */
public abstract class StormTabbedFragment extends Fragment implements StormInterface, ViewPager.OnPageChangeListener
{
	public static final int INITIAL_TAB = 0;

	@Getter protected StormPageAdapter pageAdapter;
	@Getter protected ViewPager viewPager;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(getLayoutResource(), container, false);
		viewPager = view.findViewById(R.id.view_pager);
		return view;
	}

	@Override public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		if (getActivity() == null || getArguments() == null)
		{
			return;
		}

		if (getArguments().containsKey(StormActivity.EXTRA_URI))
		{
			String pageUri = getArguments().getString(StormActivity.EXTRA_URI);
			loadPage(pageUri);
		}
		else
		{
			onLoadFail();
		}

		switchToTab(INITIAL_TAB);
	}

	/**
	 * Adds the fragmentPages to the adapter and populates the viewpager
	 *
	 * @param collection All the elements to load
	 */
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
		viewPager.addOnPageChangeListener(this);
	}

	/**
	 * Overriding classes should specify their layout ID here
	 *
	 * The layout should contain a ViewPager with id view_pager
	 */
	@Override
	@LayoutRes
	public abstract int getLayoutResource();

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
		if (getActivity() == null)
		{
			return;
		}
		Toast.makeText(getActivity(), "Failed to load page", Toast.LENGTH_SHORT).show();
		getActivity().finish();
	}

	@Override
	public void onPageScrolled(int index, float v, int i1)
	{
		// Empty
	}

	@Override
	public void onPageSelected(int index)
	{
		try
		{
			Fragment fragment = getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + index);

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

	@Override
	public void onPageScrollStateChanged(int index)
	{
		// Empty
	}

	public void switchToTab(int index)
	{
		viewPager.setCurrentItem(index, true);
	}
}
