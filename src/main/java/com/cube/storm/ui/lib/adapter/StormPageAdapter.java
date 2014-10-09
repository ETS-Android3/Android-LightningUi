package com.cube.storm.ui.lib.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentPagerAdapter;

import com.cube.storm.ui.data.FragmentIntent;

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
public class StormPageAdapter extends FragmentPagerAdapter
{
	protected final Context context;
	protected final FragmentManager manager;
	@Setter @Getter protected int index = 0;
	@Getter private List<FragmentIntent> pages = new ArrayList<FragmentIntent>(0);

	public StormPageAdapter(Context context, FragmentManager manager)
	{
		super(manager);

		this.context = context;
		this.manager = manager;
	}

	public void setPages(@NonNull Collection<FragmentIntent> pages)
	{
		this.pages = new ArrayList<FragmentIntent>(pages.size());
		this.pages.addAll(pages);
	}

	@Override public Fragment getItem(int index)
	{
		FragmentIntent intent = pages.get(index);

		return Fragment.instantiate(context, intent.getFragment().getName(), intent.getArguments());
	}

	@Override public CharSequence getPageTitle(int position)
	{
		return "";
	}

	@Override public int getCount()
	{
		return this.pages.size();
	}
}
