package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * A link property which deals with opening an external Uri, externally via an intent
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public class UriLinkProperty extends DestinationLinkProperty
{
	public static String CLASS_NAME = "UriLink";

	{ this.className = CLASS_NAME; }

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
