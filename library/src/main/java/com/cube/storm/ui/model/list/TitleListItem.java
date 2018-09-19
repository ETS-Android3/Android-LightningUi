package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.model.property.TextProperty;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A view model with a title property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data
public class TitleListItem extends ListItem
{
	public static String CLASS_NAME = "TitleListItem";

	{ this.className = CLASS_NAME; }

	protected TextProperty title;
	protected Collection<? extends LinkProperty> embeddedLinks;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
