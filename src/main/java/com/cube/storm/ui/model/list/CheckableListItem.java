package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.model.property.LinkProperty;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Collection;

import lombok.Getter;

/**
 * A view model with a boolean property
 *
 * @author Alan Le Fournis
 * @author Callum Taylor
 * @project LightningUi
 */
public class CheckableListItem extends DescriptionListItem
{
	@SerializedName("volatile") @Getter protected boolean isVolatile;

	protected CheckableListItem()
	{

	}

	public CheckableListItem(@Nullable String title, @NonNull LinkProperty... embeddedLinks)
	{
		this(title, null, embeddedLinks);
	}

	public CheckableListItem(@Nullable String title, @Nullable String description, @NonNull LinkProperty... embeddedLinks)
	{
		this(title, description, Arrays.asList(embeddedLinks));
	}

	public CheckableListItem(@Nullable String title, @Nullable String description, @NonNull Collection<? extends LinkProperty> embeddedLinks)
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
