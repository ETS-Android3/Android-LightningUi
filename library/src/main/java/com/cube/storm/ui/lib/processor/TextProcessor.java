package com.cube.storm.ui.lib.processor;

import android.support.annotation.Nullable;

import com.cube.storm.ui.model.property.TextProperty;
import com.cube.storm.util.lib.processor.Processor;

import java.util.Locale;

/**
 * Processor class used for dealing with {@link com.cube.storm.ui.model.property.TextProperty#content} strings by processing them
 * into a useful string.
 *
 * This class defaults to outputting the input
 *
 * @author Callum Taylor
 * @project Lightning
 */
public class TextProcessor extends Processor<TextProperty, String>
{
	@Nullable
	@Override public String process(@Nullable TextProperty textProperty)
	{
		if (textProperty != null && textProperty.getContent().containsKey(Locale.getDefault().getLanguage()))
		{
			return String.valueOf(textProperty.getContent().get(Locale.getDefault().getLanguage()));
		}

		return "";
	}
}
