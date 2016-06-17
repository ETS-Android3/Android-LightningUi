package com.cube.storm.ui.model.list.collection;

import android.os.Parcel;

import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.model.property.TextProperty;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class AppCollectionItem extends CollectionItem
{
	public static String CLASS_NAME = "AppCollectionItem";

	{ this.className = CLASS_NAME; }

	protected String identifier;
	protected ArrayList<ImageProperty> icon;
	protected TextProperty overlay;
	protected LinkProperty link;
	protected String name;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags){}
}
