package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.LinkProperty;

import java.util.Arrays;
import java.util.Collection;

import lombok.Getter;

/**
 * A view model with an image property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class ImageListItem extends TitleListItem
{
	@Getter protected ImageProperty image;

	protected ImageListItem()
	{
		this(null);
	}

	public ImageListItem(@Nullable ImageProperty image, @NonNull LinkProperty... embeddedLinks)
	{
		this(image, null, embeddedLinks);
	}

	public ImageListItem(@Nullable ImageProperty image, @Nullable String title, @NonNull LinkProperty... embeddedLinks)
	{
		this(image, title, Arrays.asList(embeddedLinks));
	}

	public ImageListItem(@Nullable ImageProperty image, @Nullable String title, @NonNull Collection<? extends LinkProperty> embeddedLinks)
	{
		super(title, embeddedLinks);
		this.image = image;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
