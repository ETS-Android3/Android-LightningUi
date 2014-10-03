package com.cube.storm.ui.lib.parser;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project StormUI
 */
public abstract class ViewProcessor<T> implements JsonDeserializer<T>
{
	public JsonElement preInflate(JsonElement json)
	{
		return json;
	}

	public T postInflate(T instance)
	{
		return instance;
	}

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
