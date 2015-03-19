package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.cube.storm.ui.model.property.AnimationImageProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

/**
 * Stored properties for an animated list item
 *
 * @author Luke Reed
 * @project LightningUi
 */
public class AnimatedImageListItem extends ListItem
{
	@Getter private List<AnimationImageProperty> images;

	protected AnimatedImageListItem()
	{

	}

	public AnimatedImageListItem(long delay, @NonNull String... images)
	{
		this.images = new ArrayList<>(images.length);
		for (String imageSrc: images)
		{
			this.images.add(new AnimationImageProperty(imageSrc, delay));
		}
	}

	public AnimatedImageListItem(@NonNull AnimationImageProperty... images)
	{
		this(Arrays.asList(images));
	}

	public AnimatedImageListItem(@NonNull List<AnimationImageProperty> images)
	{
		this.images = images;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
