package com.cube.storm.ui.model.list;

import android.os.Parcel;
import android.support.annotation.Nullable;

import com.cube.storm.ui.model.property.SpotlightImageProperty;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * List item class that displays a series of rotating images with text overlaid on top.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class SpotlightListItem extends ListItem
{
	protected ArrayList<SpotlightImageProperty> spotlights;

	// Legacy property name.
	@Deprecated protected ArrayList<SpotlightImageProperty> images;

	@Nullable
	public ArrayList<SpotlightImageProperty> getSpotlights()
	{
		if (spotlights != null)
		{
			return spotlights;
		}

		return images;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
