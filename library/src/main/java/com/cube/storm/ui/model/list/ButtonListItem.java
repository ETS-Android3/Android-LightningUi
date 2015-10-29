package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.ButtonProperty;

import lombok.Getter;

/**
 * A view model with a button property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class ButtonListItem extends TitleListItem
{
	@Getter protected ButtonProperty button;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
