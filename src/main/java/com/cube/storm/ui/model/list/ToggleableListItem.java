package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.model.property.LinkProperty;

import java.util.Arrays;
import java.util.Collection;

/**
 * A view model
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class ToggleableListItem extends DescriptionListItem
{
	protected ToggleableListItem()
	{
		this(null);
	}

	public ToggleableListItem(@Nullable String title, @NonNull LinkProperty... embeddedLinks)
	{
		this(title, null, embeddedLinks);
	}

	public ToggleableListItem(@Nullable String title, @Nullable String description, @NonNull LinkProperty... embeddedLinks)
	{
		this(title, description, Arrays.asList(embeddedLinks));
	}

	public ToggleableListItem(@Nullable String title, @Nullable String description, @NonNull Collection<? extends LinkProperty> embeddedLinks)
	{
		super(title, description, embeddedLinks);
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
