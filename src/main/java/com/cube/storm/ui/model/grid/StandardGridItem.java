package com.cube.storm.ui.model.grid;

import android.os.Parcel;

import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.model.property.TextProperty;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @documentation // TODO Reference flow doc
 * @project Storm
 */
public class StandardGridItem extends GridItem
{
	@Getter protected ImageProperty image;
	@Getter protected LinkProperty link;
	@Getter protected TextProperty description;
	@Getter protected TextProperty title;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
