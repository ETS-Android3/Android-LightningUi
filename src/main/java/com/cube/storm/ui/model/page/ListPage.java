package com.cube.storm.ui.model.page;

import android.os.Parcel;

import com.cube.storm.ui.model.list.ListItem;

import java.util.Collection;

import lombok.Getter;

/**
 * Basic list page model which has an array of {@link com.cube.storm.ui.model.list.ListItem} models
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class ListPage extends Page
{
	/**
	 * The array list of children {@link com.cube.storm.ui.model.list.ListItem}
	 */
	@Getter protected Collection<ListItem> children;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
