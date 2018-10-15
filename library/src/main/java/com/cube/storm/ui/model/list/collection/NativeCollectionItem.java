package com.cube.storm.ui.model.list.collection;

import android.os.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Native block item for collections
 *
 * @author Callum Taylor
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data
public class NativeCollectionItem extends CollectionItem
{
	public static String CLASS_NAME = "NativeCollectionItem";

	{ this.className = CLASS_NAME; }

	protected String name;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
