package com.cube.storm.ui.model.list;

import android.os.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * A view model
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
@AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public class ToggleableListItem extends DescriptionListItem
{
	public static String CLASS_NAME = "ToggleableListItem";
	private boolean expanded = false;

	{ this.className = CLASS_NAME; }

	/**
	 * Sets the model's expanded state to either true or false
	 *
	 * @param expanded whether or not the view has been expanded
	 */
	public void setExpanded(boolean expanded)
	{
		this.expanded = expanded;
	}

	/**
	 * Gets the model's expanded state as either true or false
	 *
	 * @return true if expanded, false if collapsed
	 */
	public boolean getExpanded()
	{
		return expanded;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
