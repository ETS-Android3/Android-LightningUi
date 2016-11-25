package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A link property which deals with opening an external Uri, internally
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor
@Accessors(chain = true) @Data
public class ExternalLinkProperty extends DestinationLinkProperty
{
	public static String CLASS_NAME = "ExternalLink";

	{ this.className = CLASS_NAME; }

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
