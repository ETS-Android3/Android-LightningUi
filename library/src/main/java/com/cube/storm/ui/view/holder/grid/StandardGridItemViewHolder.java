package com.cube.storm.ui.view.holder.grid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
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
public class StandardGridItemViewHolder extends GridViewHolder<StandardGridItem> implements OnClickListener
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

	public StandardGridItemViewHolder(View view)
	{
		super(view);

		view.setOnClickListener(this);

		image = (ImageView)view.findViewById(R.id.image);
		title = (TextView)view.findViewById(R.id.title);
		description = (TextView)view.findViewById(R.id.description);
	}

	@Override public void populateView(final StandardGridItem model)
	{
		link = model.getLink();

		image.populate(model.getImage());
		title.populate(model.getTitle());
		description.populate(model.getDescription());
	}

	@Override public void onClick(View v)
	{
		if (link != null)
		{
			UiSettings.getInstance().getLinkHandler().handleLink(image.getContext(), link);
		}
	}
}
