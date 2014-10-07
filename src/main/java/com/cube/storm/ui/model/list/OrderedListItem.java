package com.cube.storm.ui.model.list;

import android.os.Parcel;

import lombok.Getter;

/**
 * A view model
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class OrderedListItem extends DescriptionListItem
{
	@Getter private String annotation;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
