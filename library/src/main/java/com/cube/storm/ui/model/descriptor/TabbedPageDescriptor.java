package com.cube.storm.ui.model.descriptor;

import android.os.Parcel;

import com.cube.storm.ui.model.TabBarItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data
public class TabbedPageDescriptor extends PageDescriptor
{
	public static String CLASS_NAME = "TabbedPageDescriptor";

	{ this.className = CLASS_NAME; }

	protected TabBarItem tabBarItem;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
