package com.cube.storm.ui.lib.parser;

import com.cube.storm.UiSettings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class ViewParser
{
	private static Gson viewBuilder;

	/**
	 * Required to include view overrides
	 */
	public static void rebuild()
	{
		viewBuilder = null;
		getGsonBuilder();
	}

	public static Gson getGsonBuilder()
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
		return outClass.cast(getGsonBuilder().fromJson(input, outClass));
	}

	public static <T> T buildGson(JsonElement input, Class<T> outClass)
	{
		return outClass.cast(getGsonBuilder().fromJson(input, outClass));
	}
}
