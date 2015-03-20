package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.AnimationImageProperty;

import lombok.Getter;

/**
 * Stored properties for an animated list item
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class AnimationListItem extends ListItem
{
	@Getter protected AnimationImageProperty animation;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
