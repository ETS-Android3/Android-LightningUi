package com.cube.storm.ui.model.property;

import android.os.Parcel;

public class NativeImageProperty extends ImageProperty
{
	private String src;

	@Override public String getSrc()
	{
		return src;
	}

	@Override public String getFallbackSrc()
	{
		return src;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
