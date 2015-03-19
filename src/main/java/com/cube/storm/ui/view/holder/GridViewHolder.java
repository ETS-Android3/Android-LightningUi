package com.cube.storm.ui.view.holder;

import android.support.v7.widget.StaggeredGridLayoutManager.LayoutParams;
import android.view.View;

import com.cube.storm.ui.model.grid.GridItem;

/**
 * // TODO: Add class description
 *
 * @author Luke Reed
 * @project Storm
 */
public abstract class GridViewHolder<T> extends ViewHolder<T>
{
	LayoutParams params;
	public GridViewHolder(View itemView)
	{
		super(itemView);
		params = (LayoutParams)itemView.getLayoutParams();
	}

	/**
	 * Takes the model and checks if the cell is spanned, could be needed for a card or title view for example
	 * @param model
	 */
	public void checkSpan(GridItem model)
	{
		if (model.getSpanned() != null)
		{
			if(model.getSpanned())
			{
				params.setFullSpan(true);
				itemView.setLayoutParams(params);
			}
			else
			{
				params.setFullSpan(false);
				itemView.setLayoutParams(params);
			}
		}
	}
}
