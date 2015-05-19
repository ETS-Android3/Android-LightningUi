package com.cube.storm.ui.model.property;

import android.support.annotation.Nullable;

import java.util.ArrayList;

import lombok.Getter;

/**
 * Abstract message model
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public abstract class MessageLinkProperty extends ShareLinkProperty
{
	@Getter private ArrayList<String> recipients;

	public MessageLinkProperty(@Nullable String title)
	{
		super(title);
	}

	public MessageLinkProperty(@Nullable String title, @Nullable String body)
	{
		super(title, body);
	}
}
