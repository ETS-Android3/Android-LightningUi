package com.cube.storm.ui.view.holder.grid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.grid.ImageGridItem;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.holder.GridViewHolder;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;

/**
 * View holder for {@link com.cube.storm.ui.model.grid.ImageGridItem} in the adapter
 *
 * @author Luke Reed
 * @project Storm
 */
public class ImageGridItemHolder extends ViewHolderController
{

	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_grid_cell_view, parent, false);
		mViewHolder = new ImageGridItemViewHolder(view);

		return mViewHolder;
	}

	private class ImageGridItemViewHolder extends GridViewHolder<ImageGridItem>
	{
		protected ImageView image;
		protected ProgressBar progress;

		public ImageGridItemViewHolder(View view)
		{
			super(view);

			image = (ImageView)view.findViewById(R.id.image);
			progress = (ProgressBar)view.findViewById(R.id.progress);
		}

		@Override public void populateView(final ImageGridItem model)
		{
			image.populate(model.getImage(), progress);
		}
	}
}
