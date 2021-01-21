package com.cube.storm.ui.model.page;

import android.os.Parcel;

import com.cube.storm.ui.model.list.ListItem;
import com.cube.storm.ui.model.list.VideoListItem;

import java.util.ArrayList;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Basic list page model which has an array of {@link com.cube.storm.ui.model.list.ListItem} models
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public class ListPage extends Page
{
	public static String CLASS_NAME = "ListPage";

	{ this.className = CLASS_NAME; }

	protected ArrayList<VideoListItem> audio;

	/**
	 * The array list of children {@link com.cube.storm.ui.model.list.ListItem}
	 */
	protected Collection<ListItem> children;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
