package com.cube.storm.ui.model.property;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A link property which deals with opening an internal Uri
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class InternalLinkProperty extends DestinationLinkProperty
{
	private InternalLinkProperty()
	{

	}

	public InternalLinkProperty(@NonNull String destination)
	{
		this(null, destination);
	}

	public InternalLinkProperty(@Nullable String title, @NonNull String destination)
	{
		super(title, destination);
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
