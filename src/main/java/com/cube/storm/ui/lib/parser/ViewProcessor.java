package com.cube.storm.ui.lib.parser;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * View processor class used to process a json object into a model
 *
 * @author Callum Taylor
 * @project StormUI
 */
public abstract class ViewProcessor<T> implements JsonDeserializer<T>
{
	/**
	 * Called before the model is inflated from the json. Use this method to manipulate the json object.
	 *
	 * @param json The input json element
	 *
	 * @return The output json element. Defaults to return {@param json}
	 */
	public JsonElement preInflate(JsonElement json)
	{
		return json;
	}

	/**
	 * Called after the model is inflated from the json. Use this method to manipulate the created instance.
	 *
	 * @param instance The inflated instance
	 *
	 * @return The output instance. Defaults to return {@param instance}
	 */
	public T postInflate(T instance)
	{
		return instance;
	}

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
			T ret = (T)ViewParser.buildGson(arg0, item);
			ret = postInflate(ret);

			return ret;
		}

		return null;
	}
}
