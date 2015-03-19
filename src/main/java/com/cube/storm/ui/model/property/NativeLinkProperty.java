package com.cube.storm.ui.model.property;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A link property that will have a native URI scheme. You must override {@link com.cube.storm.ui.lib.factory.IntentFactory} in order to handle
 * the link.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class NativeLinkProperty extends DestinationLinkProperty
{
	private NativeLinkProperty()
	{

	}

	public NativeLinkProperty(@NonNull String destination)
	{
		this(null, destination);
	}

	public NativeLinkProperty(@Nullable String title, @NonNull String destination)
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
