package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.TextProperty;

import lombok.Getter;

/**
 * A view model with a title property
 *
 * @author Alan Le Fournis
 * @project StormUI
 */
public class TitleListItem extends ListItem
{
	@Getter protected TextProperty title;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
