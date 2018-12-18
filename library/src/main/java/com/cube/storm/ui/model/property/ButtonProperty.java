package com.cube.storm.ui.model.property;

import android.os.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * button property class.
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public class ButtonProperty extends Property
{
	public static String CLASS_NAME = "Button";

	{ this.className = CLASS_NAME; }

	private TextProperty title;
	private LinkProperty link;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel parcel, int i)
	{

	}
}
