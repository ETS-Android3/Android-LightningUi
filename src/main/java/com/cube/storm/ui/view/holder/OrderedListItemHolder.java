package com.cube.storm.ui.view.holder;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.OrderedListItem;

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

		public OrderedListItemViewHolder(View view)
		{
			super(view);

			annotation = (TextView)view.findViewById(R.id.annotation);
			title = (TextView)view.findViewById(R.id.title);
			description = (TextView)view.findViewById(R.id.description);
		}

		@Override public void populateView(OrderedListItem model)
		{
			description.setVisibility(View.GONE);
			title.setVisibility(View.GONE);

			if (model.getAnnotation() != null)
			{
				annotation.setText(model.getAnnotation());
			}

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
		}
	}
}
