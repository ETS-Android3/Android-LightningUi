package com.cube.storm.ui.view.holder.grid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.lib.EventHook;
import com.cube.storm.ui.model.grid.ImageGridItem;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.holder.GridViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.grid.ImageGridItem} in the adapter
 *
 * @author Luke Reed
 * @project LightningUi
 */
public class ImageGridItemViewHolder extends GridViewHolder<ImageGridItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public ImageGridItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_grid_cell_view, parent, false);
			return new ImageGridItemViewHolder(view);
		}
	}

	protected ImageView image;
	protected ProgressBar progress;
	protected LinkProperty link;

	public ImageGridItemViewHolder(View view)
	{
		super(view);

		image = (ImageView)view.findViewById(R.id.image);
		progress = (ProgressBar)view.findViewById(R.id.progress);
	}

	@Override public void populateView(final ImageGridItem model)
	{
		itemView.setOnClickListener(null);

		image.populate(model.getImage(), progress);

		if (link != null)
		{
			itemView.setOnClickListener(new OnClickListener()
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
