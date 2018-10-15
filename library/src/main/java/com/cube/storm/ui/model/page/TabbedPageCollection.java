package com.cube.storm.ui.model.page;

import android.os.Parcel;

import com.cube.storm.ui.model.descriptor.TabbedPageDescriptor;

import java.util.List;

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
public class TabbedPageCollection extends PageCollection
{
	public static String CLASS_NAME = "TabbedPageCollection";

	{ this.className = CLASS_NAME; }

	protected List<TabbedPageDescriptor> pages;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
