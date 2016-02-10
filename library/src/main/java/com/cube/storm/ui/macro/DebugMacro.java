package com.cube.storm.ui.macro;

import android.os.Parcel;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cube.storm.ui.model.Model;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class DebugMacro extends Macro<Void>
{
	@Override public Void execute(RecyclerView.Adapter adapter, Model model, View view)
	{
		String output = "";

		for (Macro param : params)
		{
			if (param == null) continue;

			Object resp = param.execute(adapter, model, view);

			if (resp != null)
			{
				output += String.valueOf(resp);
			}
		}

		Log.e("Debug", output);

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
