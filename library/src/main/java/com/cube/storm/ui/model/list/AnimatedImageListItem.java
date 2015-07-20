package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.AnimationImageProperty;
import com.cube.storm.ui.view.View;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Stored properties for an animated list item
 *
 * @author Luke Reed
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class AnimatedImageListItem extends ListItem
{
	{ this.className = View.AnimatedImageListItem.name(); }

	protected List<AnimationImageProperty> images;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
