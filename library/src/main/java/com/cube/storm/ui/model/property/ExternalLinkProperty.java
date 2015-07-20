package com.cube.storm.ui.model.property;

import android.os.Parcel;

import com.cube.storm.ui.view.View;

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
	{ this.className = View.ExternalLink.name(); }

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
