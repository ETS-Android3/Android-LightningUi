package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.HeaderListItem;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.list.HeaderListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class HeaderListItemViewHolder extends ViewHolder<HeaderListItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public HeaderListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_list_item_view, parent, false);
			return new HeaderListItemViewHolder(view);
		}
	}

	protected ImageView image;
	protected TextView title;
	protected TextView description;

	public HeaderListItemViewHolder(View view)
	{
		super(view);

		image = (ImageView)view.findViewById(R.id.image_view);
		title = (TextView)view.findViewById(R.id.title);
		description = (TextView)view.findViewById(R.id.description);
	}

	@Override public void populateView(final HeaderListItem model)
	{
		image.populate(model.getImage());
		title.populate(model.getTitle());
		description.populate(model.getDescription());
	}
}
