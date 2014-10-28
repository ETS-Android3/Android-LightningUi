package com.cube.storm.ui.view.holder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.model.list.List.ListFooter;

/**
 * View holder for {@link com.cube.storm.ui.model.list.List.ListFooter} in the adapter. This is the 'footer' part
 * of the {@link com.cube.storm.ui.model.list.List} model for displaying in the list view. There is a
 * counter-part for the 'header' part of the model.
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class ListFooterHolder extends Holder<ListFooter>
{
	protected TextView title;

	public ListFooterHolder(View view)
	{
		super(view);

	}

	@Override public void populateView(ListFooter model)
	{
		title.setText(UiSettings.getInstance().getTextProcessor().process(model.getFooter().getContent()));
	}

	public static ViewHolder createView(ViewGroup parent)
	{
		return new ListFooterHolder(null);
	}
}
