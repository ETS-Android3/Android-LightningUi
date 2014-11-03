package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.Getter;
import lombok.Setter;

/**
 * A link property with a destination Uri variable. Destination can be any Uri. This class is not
 * usually instantiated directly, one of its subclasses, {@link InternalLinkProperty}, {@link ExternalLinkProperty},
 * or {@link AppLinkProperty}.
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class DestinationLinkProperty extends LinkProperty
{
	@Setter @Getter protected String destination;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
