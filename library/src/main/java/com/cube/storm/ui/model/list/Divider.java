package com.cube.storm.ui.model.list;

import android.os.Parcel;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class Divider extends ListItem
{
	@Override public String getClassName()
	{
		return "_Divider";
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
