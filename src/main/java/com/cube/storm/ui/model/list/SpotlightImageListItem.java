package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.cube.storm.ui.model.property.SpotlightImageProperty;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @project LightningUi
 */
public class SpotlightImageListItem extends ListItem
{
	@Getter protected List<SpotlightImageProperty> images;

	protected SpotlightImageListItem()
	{
	}

	public SpotlightImageListItem(@NonNull SpotlightImageProperty... images)
	{
		this(Arrays.asList(images));
	}

	public SpotlightImageListItem(@NonNull List<SpotlightImageProperty> images)
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
