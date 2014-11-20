package com.cube.storm.ui.model.grid;

import android.os.Parcel;

import com.cube.storm.ui.model.list.ListItem;

import java.util.ArrayList;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @project Storm
 */
public class Grid extends GridItem
{
	@Getter protected ArrayList<ListItem> children;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
