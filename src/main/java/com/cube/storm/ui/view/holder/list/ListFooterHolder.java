package com.cube.storm.ui.view.holder.list;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.List.ListFooter;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;

/**
 * View holder for {@link com.cube.storm.ui.model.list.List.ListFooter} in the adapter. This is the 'footer' part
 * of the {@link com.cube.storm.ui.model.list.List} model for displaying in the list view. There is a
 * counter-part for the 'header' part of the model.
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class ListFooterHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_text_list_item_view, parent, false);
		mViewHolder = new ListFooterViewHolder(view);

		return mViewHolder;
	}

	private class ListFooterViewHolder extends ViewHolder<ListFooter>
	{
		protected TextView title;

		public ListFooterViewHolder(View itemView)
		{
			super(itemView);
			title = (TextView)itemView.findViewById(R.id.title);
		}

		@Override public void populateView(ListFooter model)
		{
			itemView.setVisibility(View.GONE);

			if (model.getFooter() != null)
			{
				String content = UiSettings.getInstance().getTextProcessor().process(model.getFooter().getContent());

				if (!TextUtils.isEmpty(content))
				{
					title.setText(content);
					itemView.setVisibility(View.VISIBLE);
				}
			}
		}
	}
}
