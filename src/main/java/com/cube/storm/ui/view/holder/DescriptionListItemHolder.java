package com.cube.storm.ui.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.DescriptionListItem;

/**
 * View holder for {@link com.cube.storm.ui.model.list.DescriptionListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class DescriptionListItemHolder extends Holder<DescriptionListItem>
{
	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.description_list_item_view, parent, false);

		return view;
	}

	@Override public void populateView(DescriptionListItem model)
	{
	}
}
