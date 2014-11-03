package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class VideoProperty extends Property
{
	@Getter protected String locale;
	@Getter protected ExternalLinkProperty src;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
