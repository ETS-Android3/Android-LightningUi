package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.OrderedListItem;
import com.cube.storm.ui.view.Populator;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;

/**
 * View holder for {@link com.cube.storm.ui.model.list.OrderedListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class OrderedListItemHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordered_list_item_view, parent, false);
		mViewHolder = new OrderedListItemViewHolder(view);

		return mViewHolder;
	}

	public class OrderedListItemViewHolder extends ViewHolder<OrderedListItem>
	{
		protected TextView annotation;
		protected TextView title;
		protected TextView description;
		protected LinearLayout embeddedLinksContainer;

		public OrderedListItemViewHolder(View view)
		{
			super(view);

			annotation = (TextView)view.findViewById(R.id.annotation);
			title = (TextView)view.findViewById(R.id.title);
			description = (TextView)view.findViewById(R.id.description);
			embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
		}

		@Override public void populateView(OrderedListItem model)
		{
			description.populate(model.getDescription());
			title.populate(model.getTitle());

			if (model.getAnnotation() != null)
			{
				annotation.setText(model.getAnnotation());
			}

			Populator.populate(embeddedLinksContainer, model.getEmbeddedLinks());
		}
	}
}
