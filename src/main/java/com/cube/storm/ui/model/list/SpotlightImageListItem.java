package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.SpotlightImageProperty;
import com.cube.storm.ui.view.View;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class SpotlightImageListItem extends ListItem
{
	{ this.className = View.SpotlightImageListItem.name(); }

	protected List<SpotlightImageProperty> images;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
