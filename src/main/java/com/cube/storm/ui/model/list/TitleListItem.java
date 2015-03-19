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
 * A view model with a title property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class TitleListItem extends ListItem
{
	@Getter protected TextProperty title;
	@Getter protected Collection<? extends LinkProperty> embeddedLinks;

	protected TitleListItem()
	{
		this(null);
	}

	public TitleListItem(@Nullable String title, @NonNull LinkProperty... embeddedLinks)
	{
		this(title, Arrays.asList(embeddedLinks));
	}

	public TitleListItem(@Nullable String title, @NonNull Collection<? extends LinkProperty> embeddedLinks)
	{
		this.title = new TextProperty(title);
		this.embeddedLinks = embeddedLinks;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
