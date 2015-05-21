package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A view model
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class ToggleableListItem extends DescriptionListItem
{
	{ this.className = View.ToggleableListItem.name(); }

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
