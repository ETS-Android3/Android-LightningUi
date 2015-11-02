package com.cube.storm.ui.lib.migration;

import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.lib.parser.ViewProcessor;
import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.view.View;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Migration class to allow for converting old ImageProperty class structure to new.
 * <br />
 * Register this in your {@link com.cube.storm.UiSettings.Builder} by using the following code
 * <pre>registerType(new TypeToken<ArrayList<ImageProperty>>(){}.getType(), new LegacyImageViewProcessor())</pre>
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class LegacyImageViewProcessor extends ViewProcessor<ArrayList<ImageProperty>>
{
	@NonNull @Override public JsonElement preInflate(JsonElement json)
	{
		if (json.isJsonObject())
		{
			JsonArray formatted = new JsonArray();
			JsonObject src = json.getAsJsonObject().get("src").getAsJsonObject();

			for (Map.Entry<String, JsonElement> stringJsonElementEntry : src.entrySet())
			{
				if (!stringJsonElementEntry.getKey().equals("class"))
				{
					JsonElement image = src.get(stringJsonElementEntry.getKey());

					JsonObject newImageSrc = new JsonObject();
					newImageSrc.addProperty("class", View.DestinationLink.name());
					newImageSrc.addProperty("destination", image.getAsString());

					JsonObject newImageDimensions = new JsonObject();

					int w = 0, h = 0;

					InputStream imageStream = UiSettings.getInstance().getFileFactory().loadFromUri(Uri.parse(image.getAsString()));
					if (imageStream != null)
					{
						BitmapFactory.Options opts = new BitmapFactory.Options();
						opts.inJustDecodeBounds = true;
						BitmapFactory.decodeStream(imageStream, new Rect(0, 0, 0, 0), opts);

						w = opts.outWidth;
						h = opts.outHeight;
					}

					if (w == 0 && h == 0)
					{
						if (stringJsonElementEntry.getKey().equals("x0.75"))
						{
							w = 512;
							h = 512;
						}
						else if (stringJsonElementEntry.getKey().equals("x1"))
						{
							w = 1024;
							h = 1024;
						}
						else if (stringJsonElementEntry.getKey().equals("x1.5"))
						{
							w = 1356;
							h = 1356;
						}
						else if (stringJsonElementEntry.getKey().equals("x2"))
						{
							w = 2048;
							h = 2048;
						}
					}

					newImageDimensions.addProperty("width", w);
					newImageDimensions.addProperty("height", h);

					JsonObject newImage = new JsonObject();
					newImage.addProperty("class", View.Image.name());
					newImage.add("src", newImageSrc);
					newImage.add("dimensions", newImageDimensions);

					formatted.add(newImage);
				}
			}

			return formatted;
		}

		return super.preInflate(json);
	}

	@Override public Class getClassFromName(String name)
	{
		return UiSettings.getInstance().getViewFactory().getModelForView(name);
	}

	@Override public ArrayList<ImageProperty> deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException
	{
		arg0 = preInflate(arg0);

		if (arg0.isJsonArray())
		{
			ArrayList<ImageProperty> images = new ArrayList<ImageProperty>();

			JsonArray items = arg0.getAsJsonArray();
			Iterator<JsonElement> iterator = items.iterator();
			while (iterator.hasNext())
			{
				JsonElement image = iterator.next();
				ImageProperty imageProperty = UiSettings.getInstance().getViewBuilder().build(image, ImageProperty.class);

				if (imageProperty != null)
				{
					images.add(imageProperty);
				}
			}

			return images;
		}

		return null;
	}
}
