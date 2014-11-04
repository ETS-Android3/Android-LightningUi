package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.SpotlightImageProperty;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @documentation // TODO Reference flow doc
 * @project Storm
 */
public class SpotlightImageListItem extends ListItem
{
	@Getter protected java.util.List<SpotlightImageProperty> images;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
