package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.LinkProperty;

import java.util.Arrays;
import java.util.Collection;

import com.cube.storm.ui.model.property.LinkProperty;

import lombok.Getter;

/**
 * A view model with a link and a text property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class LogoListItem extends ImageListItem
{
	@Getter protected LinkProperty link;

	protected LogoListItem()
	{
		this(null);
	}

	public LogoListItem(@Nullable ImageProperty image, @NonNull LinkProperty... embeddedLinks)
	{
		this(image, null, embeddedLinks);
	}

	public LogoListItem(@Nullable ImageProperty image, @Nullable String title, @NonNull LinkProperty... embeddedLinks)
	{
		this(image, title, Arrays.asList(embeddedLinks));
	}

	public LogoListItem(@Nullable ImageProperty image, @Nullable String title, @NonNull Collection<? extends LinkProperty> embeddedLinks)
	{
		super(image, title, embeddedLinks);
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
