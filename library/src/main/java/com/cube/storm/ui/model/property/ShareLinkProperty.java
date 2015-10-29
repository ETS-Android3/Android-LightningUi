package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.Getter;

/**
 * A link property which deals with opening an send intent with {@link com.cube.storm.ui.model.property.ShareLinkProperty#body} content
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class ShareLinkProperty extends LinkProperty
{
	@Getter protected TextProperty body;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
