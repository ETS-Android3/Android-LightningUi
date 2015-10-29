package com.cube.storm.ui.model.descriptor;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class PageDescriptor extends Model
{
	{ this.className = View.PageDescriptor.name(); }

	protected String name;
	protected String type;
	protected String src;
	protected boolean startPage;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
