package com.cube.storm.ui.model.page;

import android.os.Parcel;

import com.cube.storm.ui.model.descriptor.TabbedPageDescriptor;
import com.cube.storm.ui.view.View;

import java.util.Collection;

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
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class TabbedPageCollection extends PageCollection
{
	{ this.className = View.TabbedPageCollection.name(); }

	protected Collection<TabbedPageDescriptor> pages;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
