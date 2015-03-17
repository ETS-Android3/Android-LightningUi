package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.TextProperty;
import com.cube.storm.ui.view.View;

import lombok.Getter;

/**
 * A view model with a description property
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class TextListItem extends ListItem
{
	@Getter protected TextProperty description;

	public TextListItem()
	{
	}

	public TextListItem(String description)
	{
		this.description = new TextProperty(description);
		this.className = View.TextListItem.name();
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
