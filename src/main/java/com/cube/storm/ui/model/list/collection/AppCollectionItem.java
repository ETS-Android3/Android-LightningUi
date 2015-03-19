package com.cube.storm.ui.model.list.collection;

import android.os.Parcel;

import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.model.property.TextProperty;

import java.util.ArrayList;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class AppCollectionItem extends CollectionItem
{
	@Getter protected String identifier;
	@Getter protected ArrayList<ImageProperty> icon;
	@Getter protected TextProperty overlay;
	@Getter protected LinkProperty link;
	@Getter protected String name;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags){}
}
