package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.Getter;
import lombok.Setter;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class VideoProperty extends Property
{
	@Setter @Getter protected String locale;
	@Setter @Getter protected DestinationLinkProperty destination;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
