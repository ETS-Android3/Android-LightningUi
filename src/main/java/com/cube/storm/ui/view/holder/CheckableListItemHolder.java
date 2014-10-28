package com.cube.storm.ui.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.CheckableListItem;
import com.cube.storm.ui.view.ViewClickable;

import lombok.NonNull;

/**
 * View holder for {@link com.cube.storm.ui.model.list.CheckableListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class CheckableListItemHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkable_list_item_view, parent, false);
		mViewHolder = new CheckableListItemViewHolder(view);
		return mViewHolder;
	}

	private class CheckableListItemViewHolder extends ViewHolder<CheckableListItem> implements ViewClickable<CheckableListItem>
	{
		protected TextView title;
		protected CheckBox checkBox;

		public CheckableListItemViewHolder(View view)
		{
			super(view);

			title = (TextView)view.findViewById(R.id.title);
			checkBox = (CheckBox)view.findViewById(R.id.checkbox);
		}

		@Override public void populateView(CheckableListItem model)
		{
			if (model.getTitle() != null)
			{
				title.setText(UiSettings.getInstance().getTextProcessor().process(model.getTitle().getContent()));
			}
		}

		@Override public void onClick(@NonNull CheckableListItem model, @NonNull View view)
		{
			checkBox.setChecked(!checkBox.isChecked());
		}
	}
}
