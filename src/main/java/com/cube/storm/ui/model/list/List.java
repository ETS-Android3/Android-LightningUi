package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.TextProperty;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

/**
 * A model which hosts an array set of child {@link com.cube.storm.ui.model.list.ListItem} models. Used
 * for displaying a sub set of lists within the list view.
 *
 * This is purely a data based model and is never used when displaying content in the list, instead
 * the model is processed into 2 separate models, {@link com.cube.storm.ui.model.list.List.ListHeader} and {@link com.cube.storm.ui.model.list.List.ListFooter}
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class List extends ListItem
{
	@Getter protected TextProperty header;
	@Getter protected TextProperty footer;
	@Getter protected ArrayList<ListItem> children;

	/**
	 * This is the model used when displaying the list in the adapter. The list model is processed
	 * into a list of subviews from its {@link #children} and a {@link com.cube.storm.ui.model.list.List.ListHeader} and {@link com.cube.storm.ui.model.list.List.ListFooter}
	 * from its {@link #header} and {@link #footer}. The reason why it's done like this is because
	 * we don't want to track a single object in a list which is referenced in multiple places, so
	 * we instantiate 2 'place holder' models to handle it. {@link com.cube.storm.ui.model.list.List} isn't
	 * actually a visible view, where as it's members is.
	 */
	public static class ListHeader extends ListItem
	{
		@Getter @Setter protected TextProperty header;

		@Override public String getClassName()
		{
			return "_ListHeader";
		}

		@Override public int describeContents()
		{
			return 0;
		}

		@Override public void writeToParcel(Parcel dest, int flags)
		{
		}
	}

	/**
	 * This is the model used when displaying the list in the adapter. The list model is processed
	 * into a list of subviews from its {@link #children} and a {@link com.cube.storm.ui.model.list.List.ListHeader} and {@link com.cube.storm.ui.model.list.List.ListFooter}
	 * from its {@link #header} and {@link #footer}. The reason why it's done like this is because
	 * we don't want to track a single object in a list which is referenced in multiple places, so
	 * we instantiate 2 'place holder' models to handle it. {@link com.cube.storm.ui.model.list.List} isn't
	 * actually a visible view, where as it's members is.
	 */
	public static class ListFooter extends ListItem
	{
		@Getter @Setter protected TextProperty footer;

		@Override public String getClassName()
		{
			return "_ListFooter";
		}

		@Override public int describeContents()
		{
			return 0;
		}

		@Override public void writeToParcel(Parcel dest, int flags)
		{
		}
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
