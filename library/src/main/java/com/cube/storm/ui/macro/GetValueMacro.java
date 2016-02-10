package com.cube.storm.ui.macro;

import android.os.Parcel;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cube.storm.ui.controller.adapter.StormListAdapter;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.view.holder.ViewHolder;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@Accessors(chain = true) @Data
public class GetValueMacro extends Macro<Object>
{
	protected String objectId;

	@Override public Object execute(RecyclerView.Adapter adapter, Model model, View view)
	{
//		int position = ((StormListAdapter)adapter).getItemPosition(model);
		ViewHolder holder = (ViewHolder)((StormListAdapter)adapter).getRecyclerView().getChildViewHolder(view);

		return holder.toString();
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{
	}
}
