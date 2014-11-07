package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.VideoProperty;

import java.util.Collection;

import lombok.Getter;

/**
 * A view model with a video collection property
 *
 * @author Alan Le Fournis
 * @project StormUI
 */
public class VideoListItem extends ImageListItem
{
	@Getter protected Collection<VideoProperty> videos;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
