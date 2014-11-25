package com.cube.storm.ui.model.grid;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @project Storm
 */
public abstract class GridItem extends Model
{
	@Getter protected Boolean spanned;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
