package com.cube.storm.ui.model.property;

import android.os.Parcel;
import android.support.annotation.NonNull;

public class NativeImageProperty extends ImageProperty
{
	private String src;

	private NativeImageProperty()
	{

	}

	public NativeImageProperty(@NonNull String src)
	{
		this.src = src;
	}

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
