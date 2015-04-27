package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.LinkProperty;

import lombok.Getter;

/**
 * A view model with a link and a text property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class LogoListItem extends ImageListItem
{
	@Getter protected LinkProperty link;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
