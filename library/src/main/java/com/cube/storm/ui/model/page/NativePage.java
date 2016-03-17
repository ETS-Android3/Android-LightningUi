package com.cube.storm.ui.model.page;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.list.ListItem;
import com.cube.storm.ui.view.View;

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
@AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class NativePage extends Page
{
	{ this.className = View.NativePage.name(); }

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
