package com.cube.storm.ui.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.TitleListItem;

/**
 * View holder for {@link com.cube.storm.ui.model.list.TitleListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project StormUI
 */
public class TitleListItemHolder extends ViewHolderController
{

	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_list_item_view, parent, false);

		return new TitleListItemViewHolder(view);
	}

	private class TitleListItemViewHolder extends ViewHolder<TitleListItem>
	{
		protected TextView title;

		public TitleListItemViewHolder(View view)
		{
			super(view);
			title = (TextView)view.findViewById(R.id.title);
		}

		@Override public void populateView(TitleListItem model)
		{
			if (model.getTitle() != null)
			{
				title.setText(UiSettings.getInstance().getTextProcessor().process(model.getTitle().getContent()));
			}
		}
	}
}
