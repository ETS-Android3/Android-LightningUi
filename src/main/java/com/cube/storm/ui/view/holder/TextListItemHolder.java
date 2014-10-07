package com.cube.storm.ui.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.TextListItem;
import com.cube.storm.ui.view.TextListItemView;

/**
 * View holder for {@link com.cube.storm.ui.model.list.TextListItem} in the adapter
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class TextListItemHolder extends Holder<TextListItem>
{
	protected TextListItemView textListItemView;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_list_item_view, parent, false);
		textListItemView = (TextListItemView)view.findViewById(R.id.text);

		return view;
	}

	@Override public void populateView(TextListItem model)
	{
		textListItemView.populate(model);
	}
}
