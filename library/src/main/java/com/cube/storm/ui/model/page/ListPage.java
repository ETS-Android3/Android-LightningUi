package com.cube.storm.ui.model.page;

import android.os.Parcel;

import com.cube.storm.ui.model.list.ListItem;
import com.cube.storm.ui.model.property.VideoProperty;

import java.util.Collection;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Basic list page model which has an array of {@link com.cube.storm.ui.model.list.ListItem} models
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper = false)
public class ListPage extends Page
{
	public static String CLASS_NAME = "ListPage";
	public static final String ENGLISH_LANGUAGE_CODE = "en";

	{
		this.className = CLASS_NAME;
	}

	/**
	 * The array list of audios {@link VideoProperty}
	 */
	protected Collection<VideoProperty> audio;

	/**
	 * Returns the audio file for the user's current locale.
	 * In case storm has audios and the current locale is not supported,
	 * it will send the english audio by default.
	 * The storm locale always have the format "usa_en" so
	 * it will split the string to check the language
	 *
	 * @return The audio file if it exists
	 */
	public VideoProperty getCurrentLanguageAudioFile()
	{
		if (audio == null)
		{
			return null;
		}

		VideoProperty videoProperty;
		VideoProperty auxVideoProperty = null;

		while (audio.iterator().hasNext())
		{
			videoProperty = audio.iterator().next();
			String[] localeArray = videoProperty.getLocale().split("_");

			if (Locale.getDefault().getLanguage().equals(new Locale(localeArray[1]).getLanguage()))
			{
				return videoProperty;
			}

			if (ENGLISH_LANGUAGE_CODE.equals(new Locale(localeArray[1]).getLanguage()))
			{
				auxVideoProperty = videoProperty;
			}
		}
		return auxVideoProperty;
	}

	/**
	 * The array list of children {@link com.cube.storm.ui.model.list.ListItem}
	 */
	protected Collection<ListItem> children;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
