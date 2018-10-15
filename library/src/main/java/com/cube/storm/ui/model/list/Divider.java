package com.cube.storm.ui.model.list;

import android.os.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@AllArgsConstructor
@Accessors(chain = true) @Data
public class Divider extends ListItem
{
	public static String CLASS_NAME = "_Divider";

	{ this.className = CLASS_NAME; }

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
