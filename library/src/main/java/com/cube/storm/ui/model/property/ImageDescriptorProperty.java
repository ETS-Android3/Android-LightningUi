package com.cube.storm.ui.model.property;

import android.os.Parcel;

import com.cube.storm.ui.view.View;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class ImageDescriptorProperty extends Property
{
	{ this.className = View.Image.name(); }

	@SerializedName("x0.75") protected String x075;
	@SerializedName("x1") protected String x1;
	@SerializedName("x1.5") protected String x15;
	@SerializedName("x2") protected String x2;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel parcel, int i)
	{

	}
}
