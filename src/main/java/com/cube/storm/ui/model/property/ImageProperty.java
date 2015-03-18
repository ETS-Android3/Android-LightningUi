package com.cube.storm.ui.model.property;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;

import lombok.Getter;

public abstract class ImageProperty extends Property
{
	@Getter protected DestinationLinkProperty src;
	@Getter protected Dimensions dimensions;
	@Getter protected String mime;
	@Getter protected long size;
	@Getter protected String locale;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel parcel, int i){}

	public class Dimensions extends Model
	{
		@Getter protected int width;
		@Getter protected int height;

		@Override public int describeContents()
		{
			return 0;
		}

		@Override public void writeToParcel(Parcel dest, int flags){}
	}
}
