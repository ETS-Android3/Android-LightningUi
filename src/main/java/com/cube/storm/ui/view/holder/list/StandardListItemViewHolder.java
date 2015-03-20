package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.StandardListItem;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.Populator;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.list.StandardListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @Project LightningUi
 */
public class StandardListItemViewHolder extends ViewHolder<StandardListItem> implements OnClickListener
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public StandardListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_list_item_view, parent, false);
			return new StandardListItemViewHolder(view);
		}
	}

	protected ImageView image;
	protected TextView title;
	protected TextView description;
	protected LinkProperty link;
	protected LinearLayout embeddedLinksContainer;

	public StandardListItemViewHolder(View view)
	{
		super(view);

		view.setOnClickListener(this);

		image = (ImageView)view.findViewById(R.id.image);
		title = (TextView)view.findViewById(R.id.title);
		description = (TextView)view.findViewById(R.id.description);
		embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
	}

	@Override public void populateView(final StandardListItem model)
	{
		link = model.getLink();

		image.populate(model.getImage());
		title.populate(model.getTitle());
		description.populate(model.getDescription());
		Populator.populate(embeddedLinksContainer, model.getEmbeddedLinks());
	}

	@Override public void onClick(View v)
	{
		if (link != null)
		{
			UiSettings.getInstance().getLinkHandler().handleLink(image.getContext(), link);
		}
	}
}
