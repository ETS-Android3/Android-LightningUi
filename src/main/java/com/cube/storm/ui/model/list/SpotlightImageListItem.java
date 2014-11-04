package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.SpotlightImageProperty;

import java.util.Collection;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @documentation // TODO Reference flow doc
 * @project Storm
 */
public class SpotlightImageListItem extends ListItem
{
	private Collection<SpotlightImageProperty> images;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
