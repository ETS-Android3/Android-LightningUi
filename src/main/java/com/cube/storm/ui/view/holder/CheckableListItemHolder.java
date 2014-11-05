package com.cube.storm.ui.view.holder;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.CheckableListItem;
import com.cube.storm.ui.view.ViewClickable;

/**
 * View holder for {@link com.cube.storm.ui.model.list.CheckableListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class CheckableListItemHolder extends Holder<CheckableListItem> implements ViewClickable<CheckableListItem>
{
	protected TextView title;
	protected TextView description;
	protected CheckBox checkBox;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkable_list_item_view, parent, false);
		title = (TextView)view.findViewById(R.id.title);
		description = (TextView)view.findViewById(R.id.description);
		checkBox = (CheckBox)view.findViewById(R.id.checkbox);

		return view;
	}

	@Override public void populateView(CheckableListItem model)
	{
		description.setVisibility(View.GONE);
		title.setVisibility(View.GONE);

		if (model.getTitle() != null)
		{
			String content = UiSettings.getInstance().getTextProcessor().process(model.getTitle().getContent());

			if (!TextUtils.isEmpty(content))
			{
				title.setText(content);
				title.setVisibility(View.VISIBLE);
			}
		}

		if (model.getDescription() != null)
		{
			String content = UiSettings.getInstance().getTextProcessor().process(model.getDescription().getContent());

			if (!TextUtils.isEmpty(content))
			{
				description.setText(content);
				description.setVisibility(View.VISIBLE);
			}
		}

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
	}

	@Override public void onClick(@NonNull CheckableListItem model, @NonNull View view)
	{
		checkBox.setChecked(!checkBox.isChecked());
		checkBox.setTag(checkBox.isChecked());

		if (model.isVolatile())
		{
			SharedPreferences checkboxPrefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
			checkboxPrefs.edit().putBoolean("checkbox_" + model.getId(), checkBox.isChecked()).apply();
		}
	}
}
