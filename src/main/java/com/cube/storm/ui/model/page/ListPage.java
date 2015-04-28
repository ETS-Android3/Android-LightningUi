package com.cube.storm.ui.model.page;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.cube.storm.ui.model.list.ListItem;

import java.util.Arrays;
import java.util.Collection;

import lombok.Getter;

/**
 * Basic list page model which has an array of {@link com.cube.storm.ui.model.list.ListItem} models
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class ListPage extends Page
{
	/**
	 * The array list of children {@link com.cube.storm.ui.model.list.ListItem}
	 */
	@Getter protected Collection<ListItem> children;

	public ListPage(@NonNull String title, @NonNull String name, @NonNull ListItem... children)
	{
		this(title, name, Arrays.asList(children));
	}

	public ListPage(@NonNull String title, @NonNull String name, @NonNull Collection<ListItem> children)
	{
		super(title, name);
		this.children = children;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
