package com.cube.storm.ui.model.list;

import android.os.Parcel;
import androidx.annotation.Nullable;

import com.cube.storm.ui.model.property.AnimationFrame;
import com.cube.storm.ui.model.property.AnimationImageProperty;
import com.cube.storm.ui.model.property.TextProperty;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Stored properties for an animated list item
 *
 * @author Luke Reed
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public class AnimationListItem extends ListItem
{
	public static String CLASS_NAME = "AnimationListItem";

	{ this.className = CLASS_NAME; }

	protected AnimationImageProperty animation;

	@Deprecated protected ArrayList<AnimationFrame> images;

	protected TextProperty accessibilityLabel;

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
