package com.cube.storm.ui.view.holder.grid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.lib.EventHook;
import com.cube.storm.ui.model.grid.StandardGridItem;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.GridViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @project LightningUi
 */
public class StandardGridItemViewHolder extends GridViewHolder<StandardGridItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public StandardGridItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_grid_cell_view, parent, false);
			return new StandardGridItemViewHolder(view);
		}
	}

	protected ImageView image;
	protected TextView title;
	protected TextView description;
	protected LinkProperty link;
	protected ProgressBar progress;
	protected LinearLayout embeddedLinksContainer;

	public StandardGridItemViewHolder(View view)
	{
		super(view);

		image = (ImageView)view.findViewById(R.id.image);
		title = (TextView)view.findViewById(R.id.title);
		description = (TextView)view.findViewById(R.id.description);
		embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
		progress = (ProgressBar)view.findViewById(R.id.progress);
	}

	@Override public void populateView(final StandardGridItem model)
	{
		itemView.setOnClickListener(null);

		link = model.getLink();
		image.setVisibility(View.GONE);

		image.populate(model.getImage(), progress);

		title.populate(model.getTitle());
		description.populate(model.getDescription());

		if (link != null)
		{
			itemView.setOnClickListener(new View.OnClickListener()
			{
				@Override public void onClick(View v)
				{
					for (EventHook eventHook : UiSettings.getInstance().getEventHooks())
					{
						eventHook.onViewLinkedClicked(itemView, model, link);
					}

					UiSettings.getInstance().getLinkHandler().handleLink(image.getContext(), link);
				}
			});
		}
	}
}
