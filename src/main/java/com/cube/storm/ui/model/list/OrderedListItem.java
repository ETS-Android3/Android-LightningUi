package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.model.property.LinkProperty;

import java.util.Arrays;
import java.util.Collection;

import lombok.Getter;

/**
 * A view model
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class OrderedListItem extends DescriptionListItem
{
	@Getter protected String annotation;

	protected OrderedListItem()
	{
	}

	public OrderedListItem(@NonNull String annotation, @Nullable String title, @NonNull LinkProperty... embeddedLinks)
	{
		this(annotation, title, null, embeddedLinks);
	}

	public OrderedListItem(@NonNull String annotation, @Nullable String title, @Nullable String description, @NonNull LinkProperty... embeddedLinks)
	{
		this(annotation, title, description, Arrays.asList(embeddedLinks));
	}

	public OrderedListItem(@NonNull String annotation, @Nullable String title, @Nullable String description, @NonNull Collection<? extends LinkProperty> embeddedLinks)
	{
		super(title, description, embeddedLinks);
		this.annotation = annotation;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
