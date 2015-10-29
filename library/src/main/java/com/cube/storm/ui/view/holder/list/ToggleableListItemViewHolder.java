package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.ToggleableListItem;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.Populator;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.list.ToggleableListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @Project LightningUi
 */
public class ToggleableListItemViewHolder extends ViewHolder<ToggleableListItem> implements OnClickListener
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public ToggleableListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toggleable_list_item_view, parent, false);
			return new ToggleableListItemViewHolder(view);
		}
	}

	protected ViewGroup toggleContainer;
	protected ImageView expandIcon;
	protected TextView title;
	protected TextView description;
	protected LinearLayout embeddedLinksContainer;

	public ToggleableListItemViewHolder(View view)
	{
		super(view);

		view.setOnClickListener(this);

		toggleContainer = (ViewGroup)view.findViewById(R.id.toggle_container);
		expandIcon = (ImageView)view.findViewById(R.id.expand_icon);
		title = (TextView)view.findViewById(R.id.title);
		description = (TextView)view.findViewById(R.id.description);
		embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
	}

	@Override public void populateView(ToggleableListItem model)
	{
		title.populate(model.getTitle());
		description.populate(model.getDescription());
		Populator.populate(embeddedLinksContainer, model.getEmbeddedLinks());
		toggleContainer.setVisibility(View.GONE);
	}

	@Override public void onClick(View view)
	{
		if (toggleContainer.getVisibility() == View.GONE)
		{
			toggleContainer.setVisibility(View.VISIBLE);
			expandIcon.setImageResource(R.drawable.ic_collapse);
		}
		else
		{
			toggleContainer.setVisibility(View.GONE);
			expandIcon.setImageResource(R.drawable.ic_expand);
		}
	}
}
