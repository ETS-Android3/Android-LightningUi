package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.model.property.TextProperty;

import java.util.Arrays;
import java.util.Collection;

import lombok.Getter;

/**
 * A view model with a description property
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class DescriptionListItem extends TitleListItem
{
	@Getter protected TextProperty description;

	protected DescriptionListItem()
	{
		this(null);
	}

	public DescriptionListItem(@Nullable String description, @NonNull LinkProperty... embeddedLinks)
	{
		this(null, description, embeddedLinks);
	}

	public DescriptionListItem(@Nullable String title, @Nullable String description, @NonNull LinkProperty... embeddedLinks)
	{
		this(title, description, Arrays.asList(embeddedLinks));
	}

	public DescriptionListItem(@Nullable String title, @Nullable String description, @NonNull Collection<? extends LinkProperty> embeddedLinks)
	{
		super(title, embeddedLinks);
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
