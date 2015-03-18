package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.TitleListItem;
import com.cube.storm.ui.view.Populator;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;

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
		mViewHolder = new TitleListItemViewHolder(view);

		return mViewHolder;
	}

	public class TitleListItemViewHolder extends ViewHolder<TitleListItem>
	{
		protected TextView title;
		protected LinearLayout embeddedLinksContainer;

		public TitleListItemViewHolder(View view)
		{
			super(view);
			title = (TextView)view.findViewById(R.id.title);
			embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
		}

		@Override public void populateView(TitleListItem model)
		{
			title.populate(model.getTitle());
			Populator.populate(embeddedLinksContainer, model.getEmbeddedLinks());
		}
	}
}
