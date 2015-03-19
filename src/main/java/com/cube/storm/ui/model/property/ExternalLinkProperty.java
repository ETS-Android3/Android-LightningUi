package com.cube.storm.ui.model.property;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A link property which deals with opening an external Uri, internally
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class ExternalLinkProperty extends DestinationLinkProperty
{
	private ExternalLinkProperty()
	{

	}

	public ExternalLinkProperty(@NonNull String destination)
	{
		this(null, destination);
	}

	public ExternalLinkProperty(@Nullable String title, @NonNull String destination)
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
