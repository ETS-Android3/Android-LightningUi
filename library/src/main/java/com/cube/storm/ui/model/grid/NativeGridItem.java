package com.cube.storm.ui.model.grid;

import android.os.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Native block item for grids
 *
 * @author Callum Taylor
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public class NativeGridItem extends GridItem
{
	public static String CLASS_NAME = "NativeGridItem";

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
