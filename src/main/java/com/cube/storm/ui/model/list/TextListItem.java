package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.TextProperty;

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
