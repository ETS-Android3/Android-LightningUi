package com.cube.storm.ui.model.list;

import android.os.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A view model
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
@AllArgsConstructor
@Accessors(chain = true) @Data
public class UnorderedListItem extends DescriptionListItem
{
	public static String CLASS_NAME = "UnorderedListItem";

	{ this.className = CLASS_NAME; }

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
