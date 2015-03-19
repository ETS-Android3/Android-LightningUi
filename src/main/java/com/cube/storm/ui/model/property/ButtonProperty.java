package com.cube.storm.ui.model.property;

import android.os.Parcel;
import android.support.annotation.NonNull;

import lombok.Getter;

/**
 * button property class.
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class ButtonProperty extends Property
{
	@Getter private TextProperty title;
	@Getter private LinkProperty link;

	private ButtonProperty()
	{

	}

	public ButtonProperty(@NonNull String title, @NonNull LinkProperty link)
	{
		this.title = new TextProperty(title);
		this.link = link;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel parcel, int i)
	{

	}
}
