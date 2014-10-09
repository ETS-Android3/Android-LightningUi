package com.cube.storm.ui.view.holder;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.ToggleableListItem;
import com.cube.storm.ui.view.ViewClickable;

/**
 * View holder for {@link com.cube.storm.ui.model.list.ToggleableListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class ToggleableListItemHolder extends Holder<ToggleableListItem> implements ViewClickable<ToggleableListItem>
{
	protected ViewGroup toggleContainer;
	protected ImageView expandIcon;
	protected TextView title;
	protected TextView description;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toggleable_list_item_view, parent, false);
		toggleContainer = (ViewGroup)view.findViewById(R.id.toggle_container);
		expandIcon = (ImageView)view.findViewById(R.id.expand_icon);
		title = (TextView)view.findViewById(R.id.title);
		description = (TextView)view.findViewById(R.id.description);

		return view;
	}

	@Override public void populateView(ToggleableListItem model)
	{
		if (model.getTitle() != null)
		{
			title.setText(model.getTitle().getContent());
		}

		if (model.getDescription() != null && !TextUtils.isEmpty(model.getDescription().getContent()))
		{
			description.setText(model.getDescription().getContent());
		}

		toggleContainer.setVisibility(View.GONE);
	}

	@Override public void onClick(@NonNull ToggleableListItem model, @NonNull View view)
	{
		if (toggleContainer.getVisibility() == View.GONE)
		{
			toggleContainer.setVisibility(View.VISIBLE);
			expandIcon.setImageResource(R.drawable.ic_collapse);
		}
		else
		{
			toggleContainer.setVisibility(View.GONE);
			expandIcon.setImageResource(R.drawable.ic_expand);
		}
	}
}
