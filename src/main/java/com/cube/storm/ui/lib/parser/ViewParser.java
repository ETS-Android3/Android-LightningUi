package com.cube.storm.ui.lib.parser;

import com.cube.storm.UiSettings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * View parser used to process the json files into models to be used with the list/grid adapters
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class ViewParser
{
	private static Gson viewBuilder;

	/**
	 * Required to include view overrides
	 */
	public static void rebuild()
	{
		viewBuilder = null;
		getGson();
	}

	/**
	 * Gets the gson object with the registered storm view type adapters. Use {@link #buildGson(com.google.gson.JsonElement, Class)} or
	 * {@link #buildGson(String, Class)} to build your page/view objects.
	 *
	 * @return The gson object
	 */
	public static Gson getGson()
	{
		if (viewBuilder == null)
		{
			GsonBuilder builder = new GsonBuilder();

			for (Class instanceClass : UiSettings.getInstance().getViewProcessors().keySet())
			{
				builder.registerTypeAdapter(instanceClass, UiSettings.getInstance().getViewProcessors().get(instanceClass));
			}

			viewBuilder = builder.create();
		}

		return viewBuilder;
	}

	public static <T> T buildGson(String input, Class<T> outClass)
	{
		return outClass.cast(getGson().fromJson(input, outClass));
	}

	public static <T> T buildGson(JsonElement input, Class<T> outClass)
	{
		return outClass.cast(getGson().fromJson(input, outClass));
	}
}
