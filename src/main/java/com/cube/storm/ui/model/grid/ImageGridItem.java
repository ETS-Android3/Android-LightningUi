package com.cube.storm.ui.model.grid;

import android.os.Parcel;

import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.LinkProperty;

import lombok.Getter;

/**
 * A grid view model with an image property
 *
 * @author Luke Reed
 * @project LightningUi
 */
public class ImageGridItem extends GridItem
{
	@Getter protected ImageProperty image;
	@Getter protected LinkProperty link;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
