package com.cube.storm.ui.model.descriptor;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;

import lombok.Getter;
import lombok.Setter;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class PageDescriptor extends Model
{
	@Getter @Setter private String name;
	@Getter @Setter private String type;
	@Getter @Setter private String src;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
