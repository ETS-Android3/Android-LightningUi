package com.cube.storm.ui.model.grid;

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
 * @author Matt Allen
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class StandardGridItem extends GridItem
{
	public static String CLASS_NAME = "StandardGridItem";

	{ this.className = CLASS_NAME; }

	protected ArrayList<ImageProperty> image;
	protected LinkProperty link;
	protected TextProperty description;
	protected TextProperty title;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
