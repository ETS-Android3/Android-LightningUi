package com.cube.storm.ui.model.property;

import android.support.annotation.Nullable;

import lombok.Getter;

/**
 * Base abstract link property class. This class is never instantiated directly
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public abstract class LinkProperty extends Property
{
	@Getter protected TextProperty title;

	protected LinkProperty()
	{

	}

	protected LinkProperty(@Nullable String title)
	{
		if (title != null)
		{
			this.title = new TextProperty(title);
		}
	}
}
