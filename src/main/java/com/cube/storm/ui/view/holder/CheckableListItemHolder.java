package com.cube.storm.ui.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.CheckableListItem;

/**
 * View holder for {@link com.cube.storm.ui.model.list.CheckableListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class CheckableListItemHolder extends Holder<CheckableListItem> implements View.OnClickListener
{
	protected TextView title;
	protected CheckBox checkBox;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkable_list_item_view, parent, false);
		title = (TextView)view.findViewById(R.id.title);
		checkBox = (CheckBox)view.findViewById(R.id.checkbox);

		checkBox.setOnClickListener(this);

		return view;
	}

	@Override public void populateView(CheckableListItem model)
	{
		if (model.getTitle() != null)
		{
			title.setText(model.getTitle().getContent());
		}

		checkBox.setChecked(model.isVolatile());
	}

	@Override public void onClick(View view)
	{
		checkBox.setChecked(checkBox.isChecked() ? true : false);
	}
}
