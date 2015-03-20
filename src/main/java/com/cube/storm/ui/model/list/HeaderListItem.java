package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.TextProperty;

import lombok.Getter;

/**
 * A view model with an image and description property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class HeaderListItem extends ImageListItem
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
