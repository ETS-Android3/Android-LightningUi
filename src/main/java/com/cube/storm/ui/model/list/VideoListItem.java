package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.VideoProperty;

import java.util.Collection;

import lombok.Getter;

/**
 * A view model with a video collection property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class VideoListItem extends ImageListItem
{
	@Getter protected Collection<? extends VideoProperty> videos;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
