package com.cube.storm.ui.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.data.FragmentPackage;
import com.cube.storm.ui.lib.helper.ImageHelper;
import com.cube.storm.ui.model.descriptor.PageDescriptor;
import com.cube.storm.ui.model.descriptor.TabbedPageDescriptor;
import com.cube.storm.ui.model.page.TabbedPageCollection;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import lombok.Getter;

/**
 * Renders a Storm TabbedPageCollection as a bottom tabs view.
 */
public class StormBottomTabsFragment extends StormTabbedFragment implements AHBottomNavigation.OnTabSelectedListener
{
	@Getter protected AHBottomNavigation bottomNavigation;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = super.onCreateView(inflater, container, savedInstanceState);
		bottomNavigation = view.findViewById(R.id.bottom_tabs);
		return view;
	}

	protected void loadPages(@NonNull TabbedPageCollection collection)
	{
		super.loadPages(collection);

		// Create a bottom tab item for every item in the adapter
		for (FragmentPackage fragmentPackage : pageAdapter.getPages())
		{
			PageDescriptor pageDescriptor = fragmentPackage.getPageDescriptor();
			if (pageDescriptor instanceof TabbedPageDescriptor)
			{
				addTabBarItemToBottomTabs((TabbedPageDescriptor)pageDescriptor);
			}
		}

		bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
		bottomNavigation.setOnTabSelectedListener(this);
	}

	private void addTabBarItemToBottomTabs(TabbedPageDescriptor descriptor)
	{
		final Resources resources = getResources();
		final int iconWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, resources.getDisplayMetrics());
		final String iconSrc = ImageHelper.getImageSrc(descriptor.getTabBarItem().getImage(), iconWidth, iconWidth);
		final String itemName = UiSettings.getInstance().getTextProcessor().process(descriptor.getTabBarItem().getTitle());
		final AHBottomNavigationItem navItem = new AHBottomNavigationItem(itemName, R.drawable.ic_collapse);
		bottomNavigation.addItem(navItem);

		if (iconSrc != null)
		{
			UiSettings.getInstance().getImageLoader().loadImage(iconSrc, new SimpleImageLoadingListener()
			{
				@Override
				public void onLoadingComplete(
					String imageUri,
					View view,
					Bitmap loadedImage
				)
				{
					super.onLoadingComplete(imageUri, view, loadedImage);
					navItem.setDrawable(new BitmapDrawable(resources, loadedImage));
					bottomNavigation.refresh();
				}
			});
		}
	}

	@Override public int getLayoutResource()
	{
		return R.layout.tabbed_page_bottom_fragment_view;
	}

	@Override
	public boolean onTabSelected(
		int position,
		boolean wasSelected
	)
	{
		AHBottomNavigationItem item = bottomNavigation.getItem(position);
		viewPager.setCurrentItem(position);

		final Activity activity = getActivity();
		if (activity != null)
		{
			activity.setTitle(item.getTitle(activity));
		}

		return true;
	}

	@Override
	public void switchToTab(int index)
	{
		bottomNavigation.setCurrentItem(index);
	}
}
