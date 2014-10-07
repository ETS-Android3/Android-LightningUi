package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.Getter;

/**
 * Text property class. This class has a content string which can either be a language coded string
 * or a string. If a language manager is set, the string is replaced with it's lookup equivalent from
 * the language manager, else it is left. Do not set a language manager to disable this behaviour.
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class TextProperty extends Property
{
	@Getter private String content;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
