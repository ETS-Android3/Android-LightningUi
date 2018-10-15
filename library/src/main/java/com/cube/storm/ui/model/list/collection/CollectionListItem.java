package com.cube.storm.ui.model.list.collection;

import android.os.Parcel;

import com.cube.storm.ui.model.list.ListItem;
import com.cube.storm.ui.model.property.LinkProperty;

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
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data
public class CollectionListItem extends ListItem
{
	public static String CLASS_NAME = "CollectionListItem";

	{ this.className = CLASS_NAME; }

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
