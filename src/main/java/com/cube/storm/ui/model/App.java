package com.cube.storm.ui.model;

import android.os.Parcel;

import com.cube.storm.ui.model.descriptor.PageDescriptor;

import java.util.Collection;

import lombok.Getter;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class App extends Model
{
	@Getter private String vector;
	@Getter private Collection<PageDescriptor> map;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
