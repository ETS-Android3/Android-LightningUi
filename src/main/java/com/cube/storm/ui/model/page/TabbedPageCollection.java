package com.cube.storm.ui.model.page;

import android.os.Parcel;

import com.cube.storm.ui.model.descriptor.TabbedPageDescriptor;

import java.util.Collection;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class TabbedPageCollection extends PageCollection
{
	@Getter protected Collection<TabbedPageDescriptor> pages;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
