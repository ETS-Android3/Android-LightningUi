package com.cube.storm.ui.model;

import android.os.Parcel;

import lombok.Getter;

/**
 * Base list item class
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class TextListItem extends ListItem
{
	@Getter private TextProperty description;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
