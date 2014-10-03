package com.cube.storm.ui.controller.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * The base adapter used for displaying Storm views in a list. Using an adapter to do such a task has
 * the benefit of view recycling which makes the content smooth to scroll.
 *
 * <b>Usage</b>
 *
 * <b>Problems</b>
 * Problems can arise with this method of rendering content, specifically with render efficiency where
 * the views are not being recycled because there is only 1 of its view type in the list. This is the
 * equivalent of having all of the views inflated into a {@link android.widget.ScrollView}. The smoothness
 * of the scrolling (depending on how much content there is) diminishes with the amount of unique content
 * that the list is rendering.
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class StormListAdapter extends BaseAdapter
{
	/**
	 * The list of models of the views we are rendering in the list. This is a 1 dimensional representation
	 * of a multi-dimensional 'sub listed' array set which is outlined by the json. When setting the items
	 * in this list, the models have to be traversed in order to build the 1 dimensional list for the
	 * adapter to work correctly.
	 */
	private ArrayList<Object> items = new ArrayList<Object>();

	/**
	 * The different unique item types. This is used to tell the adapter how many unique views we're
	 * going to be rendering so it knows what and when to recycle. The list is just for index based
	 * convenience, the object type in the list is a reference to the view holder class we will use
	 * to render said view.
	 */
	private ArrayList<Class> itemTypes = new ArrayList<Class>();

	@Override public int getCount()
	{
		return items.size();
	}

	@Override public Object getItem(int position)
	{
		return items.get(position);
	}

	@Override public long getItemId(int position)
	{
		return getItem(position).hashCode();
	}

	@Override public int getViewTypeCount()
	{
		return itemTypes.size();
	}

	@Override public View getView(int position, View convertView, ViewGroup parent)
	{
		return convertView;
	}
}
