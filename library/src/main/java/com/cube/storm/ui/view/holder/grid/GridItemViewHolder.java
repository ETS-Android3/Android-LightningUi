package com.cube.storm.ui.view.holder.grid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.grid.GridItem;
import com.cube.storm.ui.view.holder.GridViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * // TODO: Add class description
 *
 * @author Luke Reed
 * @project LightningUi
 */
public class GridItemViewHolder extends GridViewHolder<GridItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public GridItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_grid_cell_view, parent, false);
			return new GridItemViewHolder(view);
		}
	}

	public GridItemViewHolder(View view)
	{
		super(view);

	}

	@Override public void populateView(GridItem model)
	{

	}
}
