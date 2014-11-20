package com.cube.storm.ui.view.holder.grid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.grid.GridItem;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;

/**
 * // TODO: Add class description
 *
 * @author Luke Reed
 * @project Storm
 */
public class GridItemHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_grid_cell_view, parent, false);
		mViewHolder = new GridItemViewHolder(view);

		return mViewHolder;
	}

	private class GridItemViewHolder extends ViewHolder<GridItem>
	{

		public GridItemViewHolder(View view)
		{
			super(view);

		}

		@Override public void populateView(GridItem model)
		{

		}
	}
}
