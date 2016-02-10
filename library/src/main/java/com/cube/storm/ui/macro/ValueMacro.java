package com.cube.storm.ui.macro;

import android.os.Parcel;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cube.storm.ui.model.Model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@Accessors(chain = true) @Data
public class ValueMacro extends Macro<Object>
{
	protected Object value;

	@Override public Object execute(RecyclerView.Adapter adapter, Model model, View view)
	{
		return value;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{
	}
}
