package com.cube.storm.ui.view.holder;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.DescriptionListItem;

/**
 * View holder for {@link com.cube.storm.ui.model.list.DescriptionListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class DescriptionListItemHolder extends ViewHolderController
{

	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.description_list_item_view, parent, false);

		return new DescriptionListItemViewHolder(view);
	}

	private class DescriptionListItemViewHolder extends ViewHolder<DescriptionListItem>
	{
		protected TextView title;
		protected TextView description;

		public DescriptionListItemViewHolder(View view)
		{
			super(view);

			title = (TextView)view.findViewById(R.id.title);
			description = (TextView)view.findViewById(R.id.description);
		}

		@Override public void populateView(DescriptionListItem model)
		{
			if (model.getTitle() != null)
			{
				title.setText(UiSettings.getInstance().getTextProcessor().process(model.getTitle().getContent()));
			}

			if (model.getDescription() != null && !TextUtils.isEmpty(model.getDescription().getContent()))
			{
				description.setText(UiSettings.getInstance().getTextProcessor().process(model.getDescription().getContent()));
				description.setVisibility(View.VISIBLE);
			}
			else
			{
				description.setVisibility(View.GONE);
			}
		}
	}
}
