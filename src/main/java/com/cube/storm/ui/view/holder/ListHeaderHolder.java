package com.cube.storm.ui.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.List.ListHeader;

/**
 * View holder for {@link com.cube.storm.ui.model.list.List.ListHeader} in the adapter. This is the 'header' part
 * of the {@link com.cube.storm.ui.model.list.List} model for displaying in the list view. There is a
 * counter-part for the 'footer' part of the model.
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class ListHeaderHolder extends Holder<ListHeader>
{
	protected TextView title;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_text_list_item_view, parent, false);
		title = (TextView)view.findViewById(R.id.title);

		return view;
	}

	@Override public void populateView(ListHeader model)
	{
		title.setText(UiSettings.getInstance().getTextProcessor().process(model.getHeader().getContent()));
	}
}
