package com.cube.storm.ui.model.page;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.property.TextProperty;

import lombok.Getter;

/**
 * Abstract page class
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class Page extends Model
{
	@Getter protected TextProperty title;
	@Getter protected String name;

	public Model[] getChildren()
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
