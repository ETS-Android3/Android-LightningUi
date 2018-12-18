package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A link property with a destination Uri variable. Destination can be any Uri. This class is not
 * usually instantiated directly, one of its subclasses, {@link InternalLinkProperty}, {@link ExternalLinkProperty}
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public class DestinationLinkProperty extends LinkProperty
{
	public static String CLASS_NAME = "DestinationLink";

	{ this.className = CLASS_NAME; }

	protected String destination;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
