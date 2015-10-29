package com.cube.storm.ui.model.property;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class ImageProperty extends Property
{
	protected DestinationLinkProperty src;
	protected Dimensions dimensions;
	protected String mime;
	protected long size;
	protected String locale;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel parcel, int i){}

	@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
	@Accessors(chain = true) @Data
	public class Dimensions extends Model
	{
		protected int width;
		protected int height;

		@Override public int describeContents()
		{
			return 0;
		}

		@Override public void writeToParcel(Parcel dest, int flags){}
	}
}
