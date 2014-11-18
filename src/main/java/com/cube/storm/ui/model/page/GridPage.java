package com.cube.storm.ui.model.page;

import android.os.Parcel;

import com.cube.storm.ui.model.grid.GridItem;

import java.util.Collection;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @project Storm
 */
public class GridPage extends Page
{
	/**
	 * The array list of children {@link com.cube.storm.ui.model.list.ListItem}
	 */
	@Getter protected Collection<GridItem> children;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
