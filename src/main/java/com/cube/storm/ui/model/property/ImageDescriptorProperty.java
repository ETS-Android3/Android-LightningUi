package com.cube.storm.ui.model.property;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class ImageDescriptorProperty extends Property
{
	@Getter protected InternalLink src;
	@Getter protected String locale;
	@Getter protected Dimensions dimensions;
	@Getter protected String mime;
	@SerializedName("size") @Getter protected long fileSize;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel parcel, int i)
	{

	}

	public class InternalLink
	{
		@Getter String destination;
	}

	public class Dimensions
	{
		@Getter protected int width;
		@Getter protected int height;
	}
}
