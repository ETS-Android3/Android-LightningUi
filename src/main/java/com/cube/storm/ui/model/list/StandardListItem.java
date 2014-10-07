package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.LinkProperty;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class StandardListItem extends DescriptionListItem
{
	@Getter protected ImageProperty image;
	@Getter protected LinkProperty link;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
