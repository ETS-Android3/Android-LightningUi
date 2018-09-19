package com.cube.storm.ui.model.page;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.list.ListItem;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Basic list page model which has an array of {@link ListItem} models
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@AllArgsConstructor
@Accessors(chain = true) @Data
public class NativePage extends Page
{
	public static String CLASS_NAME = "NativePage";

	{ this.className = CLASS_NAME; }

	@Override public Collection<? extends Model> getChildren()
	{
		return null;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
