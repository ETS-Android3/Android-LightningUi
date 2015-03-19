package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.model.property.TextProperty;

import java.util.Arrays;
import java.util.Collection;

import lombok.Getter;

/**
 * A view model with an image and description property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class HeaderListItem extends ImageListItem
{
	@Getter protected TextProperty description;

	public HeaderListItem(@NonNull String title, @NonNull LinkProperty... embeddedLinks)
	{
		this(title, null, embeddedLinks);
	}

	public HeaderListItem(@NonNull String title, @Nullable ImageProperty image, @NonNull LinkProperty... embeddedLinks)
	{
		this(title, image, null, embeddedLinks);
	}

	public HeaderListItem(@NonNull String title, @Nullable ImageProperty image, @Nullable String description, @NonNull LinkProperty... embeddedLinks)
	{
		this(title, image, description, Arrays.asList(embeddedLinks));
	}

	public HeaderListItem(@NonNull String title, @Nullable ImageProperty image, @Nullable String description, @NonNull Collection<? extends LinkProperty> embeddedLinks)
	{
		super(image, title, embeddedLinks);
		if (description != null)
		{
			this.description = new TextProperty(description);
		}
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
