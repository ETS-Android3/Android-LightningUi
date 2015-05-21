package com.cube.storm.ui.model.grid;

import android.os.Parcel;

import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A grid view model with an image property
 *
 * @author Luke Reed
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class ImageGridItem extends GridItem
{
	{ this.className = View.ImageGridItem.name(); }

	protected ImageProperty image;
	protected LinkProperty link;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
