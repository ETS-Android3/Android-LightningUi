package com.cube.storm.ui.view.holder.list;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.CheckableListItem;
import com.cube.storm.ui.view.Populator;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.list.CheckableListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class CheckableListItemViewHolder extends ViewHolder<CheckableListItem> implements OnClickListener
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public CheckableListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkable_list_item_view, parent, false);
			return new CheckableListItemViewHolder(view);
		}
	}

	protected TextView title;
	protected TextView description;
	protected CheckBox checkBox;
	protected LinearLayout embeddedLinksContainer;
	protected boolean isVolatile = false;
	protected String modelId;

	public CheckableListItemViewHolder(View view)
	{
		super(view);

		view.setOnClickListener(this);

		title = (TextView)view.findViewById(R.id.title);
		description = (TextView)view.findViewById(R.id.description);
		checkBox = (CheckBox)view.findViewById(R.id.checkbox);
		embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
	}

	@Override public void populateView(CheckableListItem model)
	{
		title.populate(model.getTitle());
		description.populate(model.getDescription());

		modelId = model.getId();
		isVolatile = model.isVolatile();

		if (checkBox.getTag() != null)
		{
			checkBox.setChecked((Boolean)checkBox.getTag());
		}
		else
		{
			if (model.isVolatile())
			{
				SharedPreferences checkboxPrefs = PreferenceManager.getDefaultSharedPreferences(checkBox.getContext());
				checkBox.setChecked(checkboxPrefs.getBoolean("checkbox_" + model.getId(), false));
				checkBox.setTag(checkBox.isChecked());
			}
		}

		Populator.populate(embeddedLinksContainer, model.getEmbeddedLinks());
	}

	@Override public void onClick (View v)
	{
		checkBox.setChecked(!checkBox.isChecked());
		checkBox.setTag(checkBox.isChecked());

		if (isVolatile)
		{
			SharedPreferences checkboxPrefs = PreferenceManager.getDefaultSharedPreferences(title.getContext());
			checkboxPrefs.edit().putBoolean("checkbox_" + modelId, checkBox.isChecked()).apply();
		}
	}
}
