package com.cube.storm.ui.lib.processor;

import android.support.annotation.Nullable;

import com.cube.storm.util.lib.processor.Processor;

/**
 * Processor class used for dealing with {@link com.cube.storm.ui.model.property.TextProperty#content} strings by processing them
 * into a useful string.
 *
 * This class defaults to outputting the input
 *
 * @author Callum Taylor
 * @project Lightning
 */
public class TextProcessor extends Processor<String, String>
{
	@Nullable
	@Override public String process(@Nullable String textProperty)
	{
		if (textProperty == null)
		{
			return "";
		}

		return String.valueOf(textProperty);
	}
}
