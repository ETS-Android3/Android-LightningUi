package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.model.property.ButtonProperty;
import com.cube.storm.ui.model.property.LinkProperty;

import java.util.Arrays;
import java.util.Collection;

import lombok.Getter;

/**
 * A view model with a button property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class ButtonListItem extends TitleListItem
{
	@Getter protected ButtonProperty button;

	protected ButtonListItem()
	{
	}

	public ButtonListItem(@NonNull String buttonText, @NonNull LinkProperty buttonLink, @NonNull LinkProperty... embeddedLinks)
	{
		this(buttonText, buttonLink, null, embeddedLinks);
	}

	public ButtonListItem(@NonNull String buttonText, @NonNull LinkProperty buttonLink, @Nullable String title, @NonNull LinkProperty... embeddedLinks)
	{
		this(buttonText, buttonLink, title, Arrays.asList(embeddedLinks));
	}

	public ButtonListItem(@NonNull String buttonText, @NonNull LinkProperty buttonLink, @Nullable String title, @NonNull Collection<? extends LinkProperty> embeddedLinks)
	{
		super(title, embeddedLinks);
		this.button = new ButtonProperty(buttonText, buttonLink);
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
