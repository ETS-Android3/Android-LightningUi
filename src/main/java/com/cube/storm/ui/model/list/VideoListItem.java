package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.model.property.VideoProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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

	protected VideoListItem()
	{
		this((String)null);
	}

	public VideoListItem(@NonNull VideoProperty... videos)
	{
		this(null, videos);
	}

	public VideoListItem(@Nullable String title, @NonNull VideoProperty... videos)
	{
		this(title, null, videos);
	}

	public VideoListItem(@Nullable String title, @Nullable ImageProperty image, @NonNull VideoProperty... videos)
	{
		this(title, image, Arrays.asList(videos), Collections.<LinkProperty>emptyList());
	}

	public VideoListItem(@Nullable String title,
						 @Nullable ImageProperty image,
						 @NonNull Collection<? extends VideoProperty> videos,
						 @NonNull Collection<? extends LinkProperty> embeddedLinks)
	{
		super(image, title, embeddedLinks);
		this.videos = videos;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
