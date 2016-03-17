package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class Divider extends ListItem
{
	{ this.className = View._Divider.name(); }

	@Override public String getClassName()
	{
		return "_Divider";
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
