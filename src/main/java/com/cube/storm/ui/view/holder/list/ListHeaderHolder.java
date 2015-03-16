package com.cube.storm.ui.view.holder.list;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.List.ListHeader;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;

/**
 * View holder for {@link com.cube.storm.ui.model.list.List.ListHeader} in the adapter. This is the 'header' part
 * of the {@link com.cube.storm.ui.model.list.List} model for displaying in the list view. There is a
 * counter-part for the 'footer' part of the model.
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class ListHeaderHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_text_list_item_view, parent, false);
		mViewHolder = new ListHeaderViewHolder(view);

		return mViewHolder;
	}

	private class ListHeaderViewHolder extends ViewHolder<ListHeader>
	{
		protected TextView title;

		public ListHeaderViewHolder(View view)
		{
			super(view);
			title = (TextView)view.findViewById(R.id.title);
		}

		@Override public void populateView(ListHeader model)
		{
			itemView.setVisibility(View.GONE);

			if (model.getHeader() != null)
			{
				String content = UiSettings.getInstance().getTextProcessor().process(model.getHeader().getContent());

				if (!TextUtils.isEmpty(content))
				{
					title.setText(content);
					itemView.setVisibility(View.VISIBLE);
				}
			}
		}
	}
}
