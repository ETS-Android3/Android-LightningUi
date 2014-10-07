package com.cube.storm.ui.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.DescriptionListItem;

/**
 * View holder for {@link com.cube.storm.ui.model.list.DescriptionListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class CheckableListItemHolder extends Holder<DescriptionListItem>
{
	protected TextView title;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkable_list_item_view, parent, false);
		title = (TextView)view.findViewById(R.id.title);

		return view;
	}

	@Override public void populateView(DescriptionListItem model)
	{
		if (model.getTitle() != null)
		{
			title.setText(model.getTitle().getContent());
		}
	}
}
