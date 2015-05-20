package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * A view model with a boolean property
 *
 * @author Alan Le Fournis
 * @author Callum Taylor
 * @project LightningUi
 */
public class CheckableListItem extends DescriptionListItem
{
	@SerializedName("volatile") @Getter protected boolean isVolatile;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
