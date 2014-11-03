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
	@Getter @Setter protected String name;
	@Getter @Setter protected String type;
	@Getter @Setter protected String src;
	@Getter @Setter protected boolean startPage;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
