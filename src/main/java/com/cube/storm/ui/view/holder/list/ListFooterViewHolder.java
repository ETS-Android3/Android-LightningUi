package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.List.ListFooter;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.list.List.ListFooter} in the adapter. This is the 'footer' part
 * of the {@link com.cube.storm.ui.model.list.List} model for displaying in the list view. There is a
 * counter-part for the 'header' part of the model.
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class ListFooterViewHolder extends ViewHolder<ListFooter>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public ListFooterViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_text_list_item_view, parent, false);
			return new ListFooterViewHolder(view);
		}
	}

	protected TextView title;

	public ListFooterViewHolder(View itemView)
	{
		super(itemView);
		title = (TextView)itemView.findViewById(R.id.title);
	}

	@Override public void populateView(ListFooter model)
	{
		title.populate(model.getFooter());
	}
}
