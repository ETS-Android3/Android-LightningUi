package com.cube.storm.ui.model.list;

import android.os.Parcel;

/**
 * A view model with a link and a text property
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class LogoListItem extends ImageListItem
{
	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
