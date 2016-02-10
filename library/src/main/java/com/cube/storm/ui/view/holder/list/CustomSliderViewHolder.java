package com.cube.storm.ui.view.holder.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.event.Event;
import com.cube.storm.ui.model.list.CustomSlider;
import com.cube.storm.ui.model.list.StandardListItem;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link StandardListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @Project LightningUi
 */
public class CustomSliderViewHolder extends ViewHolder<CustomSlider>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public CustomSliderViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_list_item_view, parent, false);
			return new CustomSliderViewHolder(adapter, view);
		}
	}

	protected TextView title;
	protected RecyclerView.Adapter adapter;

	public CustomSliderViewHolder(RecyclerView.Adapter adapter, View view)
	{
		super(view);
		this.adapter = adapter;
	}

	@Override public void populateView(final CustomSlider model)
	{
		if (model.getEvents() != null)
		{
			for (Event event : model.getEvents())
			{
				event.register(adapter, model, itemView);
			}
		}
	}
}
