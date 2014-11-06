package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.AnimationImageProperty;

import lombok.Getter;

/**
 * Stored properties for an animated list item
 *
 * @author Luke Reed
 * @project Storm
 */
public class AnimatedImageListItem extends ListItem
{
	@Getter private java.util.List<AnimationImageProperty> images;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
