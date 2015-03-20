package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.ImageProperty;

import lombok.Getter;

/**
 * A view model with an image property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class ImageListItem extends TitleListItem
{
	@Getter protected ImageProperty image;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
