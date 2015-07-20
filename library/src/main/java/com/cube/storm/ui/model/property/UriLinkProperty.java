package com.cube.storm.ui.model.property;

import android.os.Parcel;

import com.cube.storm.ui.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A link property which deals with opening an external Uri, externally via an intent
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class UriLinkProperty extends DestinationLinkProperty
{
	{ this.className = View.UriLink.name(); }

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
