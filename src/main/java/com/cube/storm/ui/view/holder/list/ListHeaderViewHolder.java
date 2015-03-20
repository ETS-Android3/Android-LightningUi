package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.List.ListHeader;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.list.List.ListHeader} in the adapter. This is the 'header' part
 * of the {@link com.cube.storm.ui.model.list.List} model for displaying in the list view. There is a
 * counter-part for the 'footer' part of the model.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class ListHeaderViewHolder extends ViewHolder<ListHeader>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public ListHeaderViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_text_list_item_view, parent, false);
			return new ListHeaderViewHolder(view);
		}
	}

	protected TextView title;

	public ListHeaderViewHolder(View view)
	{
		super(view);
		title = (TextView)view.findViewById(R.id.title);
	}

	@Override public void populateView(ListHeader model)
	{
		title.populate(model.getHeader());
	}
}
