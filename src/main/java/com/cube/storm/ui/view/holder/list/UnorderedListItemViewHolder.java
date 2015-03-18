package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.UnorderedListItem;
import com.cube.storm.ui.view.Populator;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.list.UnorderedListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class UnorderedListItemViewHolder extends ViewHolder<UnorderedListItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public UnorderedListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unordered_list_item_view, parent, false);
			return new UnorderedListItemViewHolder(view);
		}
	}

	protected TextView title;
	protected TextView description;
	protected LinearLayout embeddedLinksContainer;

	public UnorderedListItemViewHolder(View view)
	{
		super(view);

		title = (TextView)view.findViewById(R.id.title);
		description = (TextView)view.findViewById(R.id.description);
		embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
	}

	@Override public void populateView(final UnorderedListItem model)
	{
		title.populate(model.getTitle());
		description.populate(model.getDescription());
		Populator.populate(embeddedLinksContainer, model.getEmbeddedLinks());
	}
}
