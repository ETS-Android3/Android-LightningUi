package com.cube.storm.ui.model.property;

import android.support.annotation.NonNull;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @project LightningUi
 */
public class AnimationImageProperty extends BundleImageProperty
{
	@Getter protected long delay;

	protected AnimationImageProperty()
	{
	}

	public AnimationImageProperty(@NonNull String src, long delay)
	{
		this(new ImageDescriptorProperty(src), delay);
	}

	public AnimationImageProperty(@NonNull ImageDescriptorProperty src, long delay)
	{
		super(src);
		this.delay = delay;
	}

}
