package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Text property class. This class has a content string which can either be a language coded string
 * or a string. If a language manager is set, the string is replaced with it's lookup equivalent from
 * the language manager, else it is left. Do not set a language manager to disable this behaviour.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class TextProperty extends Property
{
	{ this.className = "Text"; }

	protected String content;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
