package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class VideoProperty extends Property
{
	@Getter protected String locale;
	@Getter protected ExternalLinkProperty src;

	private VideoProperty()
	{

	}

	public VideoProperty(String locale, String destinationSrc)
	{
		this.locale = locale;
		this.src = new ExternalLinkProperty(destinationSrc);
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
