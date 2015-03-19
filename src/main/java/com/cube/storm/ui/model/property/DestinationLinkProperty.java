package com.cube.storm.ui.model.property;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.Getter;

/**
 * A link property with a destination Uri variable. Destination can be any Uri. This class is not
 * usually instantiated directly, one of its subclasses, {@link InternalLinkProperty}, {@link ExternalLinkProperty},
 * or {@link AppLinkProperty}.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class DestinationLinkProperty extends LinkProperty
{
	@Getter protected String destination;

	protected DestinationLinkProperty()
	{

	}

	public DestinationLinkProperty(@NonNull String destination)
	{
		this(null, destination);
	}

	public DestinationLinkProperty(@Nullable String title, @NonNull String destination)
	{
		super(title);
		this.destination = destination;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
