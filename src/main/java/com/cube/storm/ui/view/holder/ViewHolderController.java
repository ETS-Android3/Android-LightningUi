package com.cube.storm.ui.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Controller for the ViewHolder that will populate the view. This will instantiate the ViewHolder
 * with the necessary view which is then retrievable with {@link ViewHolderController#getViewHolder()}
 *
 * @author Callum Taylor
 * @project Storm Test
 */
public abstract class ViewHolderController
{
	public ViewHolder<?> mViewHolder;

	public abstract RecyclerView.ViewHolder createViewHolder(ViewGroup parent);

	/**
	 * Retrieve the ViewHolder which is a member of the controller
	 * @return The ViewHolder to populate the view
	 */
	public RecyclerView.ViewHolder getViewHolder()
	{
		return mViewHolder;
	}
}
