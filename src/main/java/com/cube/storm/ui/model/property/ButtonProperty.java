package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.Getter;

/**
 * button property class.
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class ButtonProperty extends Property
{
	@Getter private TextProperty title;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel parcel, int i)
	{

	}
}
