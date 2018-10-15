package com.cube.storm.ui.model.page;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.grid.Grid;

import java.util.Collection;

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
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data
public class GridPage extends Page
{
	public static String CLASS_NAME = "GridPage";

	{ this.className = CLASS_NAME; }

	/**
	 * The array list of children {@link com.cube.storm.ui.model.list.ListItem}
	 */
	protected Grid grid;

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
