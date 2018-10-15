package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A link property which deals with opening an send intent with {@link com.cube.storm.ui.model.property.ShareLinkProperty#body} content
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data
public class ShareLinkProperty extends LinkProperty
{
	public static String CLASS_NAME = "ShareLink";

	{ this.className = CLASS_NAME; }

	protected TextProperty body;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
