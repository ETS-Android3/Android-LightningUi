package com.cube.storm.ui.model.property;

import android.os.Parcel;

import com.cube.storm.ui.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class NativeImageProperty extends ImageProperty
{
	{ this.className = View.NativeImage.name(); }

	protected String src;

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
