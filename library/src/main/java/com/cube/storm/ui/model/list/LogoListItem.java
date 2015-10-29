package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.LinkProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A view model with a link and a text property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class LogoListItem extends ImageListItem
{
	protected LinkProperty link;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
