package com.cube.storm.ui.model.grid;

import android.os.Parcel;

import com.cube.storm.ui.model.list.ListItem;
import com.cube.storm.ui.view.View;

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
public class Grid extends GridItem
{
	{ this.className = View.Grid.name(); }

	protected ArrayList<ListItem> children;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
