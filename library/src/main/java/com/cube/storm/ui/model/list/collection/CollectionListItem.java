package com.cube.storm.ui.model.list.collection;

import android.os.Parcel;

import com.cube.storm.ui.model.list.ListItem;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.view.View;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class CollectionListItem extends ListItem
{
	{ this.className = View.CollectionListItem.name(); }

	protected Collection<CollectionItem> cells;
	protected Collection<LinkProperty> embeddedLinks;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
