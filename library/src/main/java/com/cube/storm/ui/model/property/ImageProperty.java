package com.cube.storm.ui.model.property;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public class ImageProperty extends Property
{
	public static String CLASS_NAME = "Image";

	{ this.className = CLASS_NAME; }

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

	@NoArgsConstructor @AllArgsConstructor
	@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
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
