package com.cube.storm.ui.macro;

import android.os.Parcel;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cube.storm.ui.model.Model;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class AdditionMacro extends Macro<Number>
{
	@Override public Number execute(RecyclerView.Adapter adapter, Model model, View view)
	{
		Number output = 0;

		for (Macro param : params)
		{
			Object resp = param.execute(adapter, model, view);

			if (resp instanceof Integer)
			{
				output = output.intValue() + (Integer)resp;
			}
			else if (resp instanceof Float)
			{
				output = output.floatValue() + (Float)resp;
			}
			else if (resp instanceof Double)
			{
				output = output.doubleValue() + (Double)resp;
			}
		}

		return output;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{
	}
}
