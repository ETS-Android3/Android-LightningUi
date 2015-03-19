package com.cube.storm.ui.model.property;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @project LightningUi
 */
public class SpotlightImageProperty extends AnimationImageProperty
{
	@Getter private TextProperty text;
	@Getter private LinkProperty link;

	protected SpotlightImageProperty()
	{
	}

	public SpotlightImageProperty(@NonNull String src, long delay, @NonNull String text)
	{
		this(src, delay, text, (LinkProperty)null);
	}

	public SpotlightImageProperty(@NonNull String src, long delay, @NonNull String text, @Nullable String linkDestination)
	{
		this(new ImageDescriptorProperty(src), delay, text, linkDestination);
	}

	public SpotlightImageProperty(@NonNull String src, long delay, @NonNull String text, @Nullable LinkProperty link)
	{
		this(new ImageDescriptorProperty(src), delay, text, link);
	}

	public SpotlightImageProperty(@NonNull ImageDescriptorProperty src, long delay, @NonNull String text, @Nullable String linkDestination)
	{
		this(src, delay, text, linkDestination != null ? new DestinationLinkProperty(linkDestination) : null);
	}

	public SpotlightImageProperty(@NonNull ImageDescriptorProperty src, long delay, @NonNull String text, @Nullable LinkProperty link)
	{
		super(src, delay);
		this.text = new TextProperty(text);

		if (link != null)
		{
			this.link = link;
		}
	}
}
