package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.TextProperty;

import lombok.Getter;

/**
 * A view model with a description property
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class DescriptionListItem extends TitleListItem
{
	@Getter protected TextProperty description;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
