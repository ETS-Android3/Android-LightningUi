package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.SpotlightImageProperty;

import java.util.ArrayList;

import lombok.Getter;

/**
 * List item class that displays a series of rotating images with text overlaid on top.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class SpotlightListItem extends ListItem
{
	@Getter protected ArrayList<SpotlightImageProperty> spotlights;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
