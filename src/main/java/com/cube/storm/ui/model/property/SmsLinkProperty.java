package com.cube.storm.ui.model.property;

import android.support.annotation.Nullable;

public class SmsLinkProperty extends MessageLinkProperty
{
	public SmsLinkProperty(@Nullable String title)
	{
		super(title);
	}

	public SmsLinkProperty(@Nullable String title, @Nullable String body)
	{
		super(title, body);
	}
}
