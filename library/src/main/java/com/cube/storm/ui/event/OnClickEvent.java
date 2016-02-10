package com.cube.storm.ui.event;

import android.os.Parcel;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cube.storm.ui.macro.Macro;
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
public class OnClickEvent extends Event
{
	@Override public void register(final RecyclerView.Adapter adapter, final Model model, final View view)
	{
		if (macros != null && macros.size() > 0)
		{
			view.setOnClickListener(new View.OnClickListener()
			{
				@Override public void onClick(View v)
				{
					for (Macro macro : getMacros())
					{
						macro.execute(adapter, model, view);
					}
				}
			});
		}
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
