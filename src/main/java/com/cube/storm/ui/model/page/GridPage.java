package com.cube.storm.ui.model.page;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.grid.Grid;

import java.util.Collection;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @project Storm
 */
public class GridPage extends Page
{
	/**
	 * The array list of children {@link com.cube.storm.ui.model.list.ListItem}
	 */
	@Getter protected Grid grid;

	@Override public Collection<? extends Model> getChildren()
	{
		return null;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
