package com.cube.storm.ui.model.property;

import android.os.Parcel;

/**
 * A link property which deals with opening an external Uri, internally
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class ExternalLinkProperty extends DestinationLinkProperty
{
	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
