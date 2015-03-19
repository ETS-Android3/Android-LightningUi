package com.cube.storm.ui.model.property;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class ImageDescriptorProperty extends Property
{
	@SerializedName("x0.75") @Getter protected String x075;
	@SerializedName("x1") @Getter protected String x1;
	@SerializedName("x1.5") @Getter protected String x15;
	@SerializedName("x2") @Getter protected String x2;


	private ImageDescriptorProperty()
	{

	}

	public ImageDescriptorProperty(@NonNull String src)
	{
		this(src, src, src, src);
	}

	public ImageDescriptorProperty(@NonNull String x075, @NonNull String x1, @NonNull String x15, @NonNull String x2)
	{
		this.x075 = x075;
		this.x1 = x1;
		this.x15 = x15;
		this.x2 = x2;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel parcel, int i)
	{

	}
}
