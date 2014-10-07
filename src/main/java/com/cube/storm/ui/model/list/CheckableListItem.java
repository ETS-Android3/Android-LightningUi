package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * A view model with a boolean property
 *
 * @author Alan Le Fournis
 * @project StormUI
 */
public class CheckableListItem extends TitleListItem
{
	@SerializedName("volatile") @Getter private boolean isVolatile;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
