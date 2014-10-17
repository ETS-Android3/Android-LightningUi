package com.cube.storm.ui.lib.parser;

import com.cube.storm.UiSettings;
import com.cube.storm.util.lib.processor.GsonProcessor;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * View processor class used to process a json object into a model
 *
 * @author Callum Taylor
 * @project StormUI
 */
public abstract class ViewProcessor<T> extends GsonProcessor<T>
{
	/**
	 * Method called when resolving what class to inflate for the json element. Use this method to override
	 * what class is used when inflating.
	 *
	 * @param name The name of the class
	 *
	 * @return The class to inflate into
	 */
	public abstract Class<? extends T> getClassFromName(String name);

	@Override public final T deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException
	{
		arg0 = preInflate(arg0);

		String className = arg0.getAsJsonObject().get("class").getAsString();
		Class<? extends T> item = getClassFromName(className);

		if (item != null)
		{
			T ret = (T)UiSettings.getInstance().getViewBuilder().build(arg0, item);
			ret = postInflate(ret);

			return ret;
		}

		return null;
	}
}
