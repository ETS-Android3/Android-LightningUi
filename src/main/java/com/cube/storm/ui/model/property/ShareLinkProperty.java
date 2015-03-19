package com.cube.storm.ui.model.property;

import android.os.Parcel;
import android.support.annotation.Nullable;

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

	private ShareLinkProperty()
	{

	}

	public ShareLinkProperty(@Nullable String title)
	{
		this(title, null);
	}

	public ShareLinkProperty(@Nullable String title, @Nullable String body)
	{
		super(title);
		if (body != null)
		{
			this.body = new TextProperty(body);
		}
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
