package com.cube.storm.ui.lib.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.data.FragmentPackage;
import com.cube.storm.ui.model.descriptor.TabbedPageDescriptor;
import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.view.PagerSlidingTabStrip.IconTabProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class StormPageAdapter extends FragmentPagerAdapter implements IconTabProvider
{
	protected final Context context;
	protected final FragmentManager manager;
	@Setter @Getter protected int index = 0;
	@Getter private List<FragmentPackage> pages = new ArrayList<FragmentPackage>(0);

	public StormPageAdapter(Context context, FragmentManager manager)
	{
		super(manager);

		this.context = context;
		this.manager = manager;
	}

	public void setPages(@NonNull Collection<FragmentPackage> pages)
	{
		this.pages = new ArrayList<FragmentPackage>(pages.size());
		this.pages.addAll(pages);
	}

	@Override public Fragment getItem(int index)
	{
		FragmentIntent intent = pages.get(index).getFragmentIntent();

		return Fragment.instantiate(context, intent.getFragment().getName(), intent.getArguments());
	}

	@Override public CharSequence getPageTitle(int position)
	{
		FragmentPackage fragmentPackage = pages.get(position % pages.size());

		if (fragmentPackage.getPageDescriptor() != null)
		{
			if (fragmentPackage.getPageDescriptor() instanceof TabbedPageDescriptor)
			{
				if (((TabbedPageDescriptor)fragmentPackage.getPageDescriptor()).getTabBarItem().getTitle() != null)
				{
					return UiSettings.getInstance().getTextProcessor().process(((TabbedPageDescriptor)fragmentPackage.getPageDescriptor()).getTabBarItem().getTitle().getContent());
				}
			}

			String tabName = "";

			if (!TextUtils.isEmpty(fragmentPackage.getPageDescriptor().getName()))
			{
				tabName = fragmentPackage.getPageDescriptor().getName();
			}

			return UiSettings.getInstance().getTextProcessor().process(tabName);
		}
		else if (!TextUtils.isEmpty(fragmentPackage.getFragmentIntent().getTitle()))
		{
			return fragmentPackage.getFragmentIntent().getTitle();
		}

		return "";
	}

	@Override public int getPageIconResId(int position)
	{
		return 0;
	}

	@Override public Bitmap getPageIconBitmap(int position)
	{
		FragmentPackage fragmentPackage = pages.get(position % pages.size());
		Bitmap image = null;

		if (fragmentPackage.getPageDescriptor() instanceof TabbedPageDescriptor)
		{
			if (((TabbedPageDescriptor)fragmentPackage.getPageDescriptor()).getTabBarItem().getImage() != null)
			{
				ImageProperty imageProperty = ((TabbedPageDescriptor)fragmentPackage.getPageDescriptor()).getTabBarItem().getImage();

				image = UiSettings.getInstance().getImageLoader().loadImageSync(imageProperty.getSrc());

				if (image == null && !TextUtils.isEmpty(imageProperty.getFallbackSrc()))
				{
					image = UiSettings.getInstance().getImageLoader().loadImageSync(imageProperty.getFallbackSrc());
				}

				return image;
			}
		}


		return null;
	}

	@Override public int getCount()
	{
		return this.pages.size();
	}
}
