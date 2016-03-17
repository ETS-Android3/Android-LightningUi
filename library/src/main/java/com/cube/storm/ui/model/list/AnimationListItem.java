package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.Nullable;

import com.cube.storm.ui.model.property.AnimationFrame;
import com.cube.storm.ui.model.property.AnimationImageProperty;
import com.cube.storm.ui.view.View;

import java.util.ArrayList;

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
public class AnimationListItem extends ListItem
{
	{ this.className = View.AnimationListItem.name(); }

	protected AnimationImageProperty animation;

	@Deprecated protected ArrayList<AnimationFrame> images;

	@Nullable
	public AnimationImageProperty getAnimation()
	{
		if (animation == null && images != null)
		{
			animation = new AnimationImageProperty(true, images);
		}

		return animation;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
