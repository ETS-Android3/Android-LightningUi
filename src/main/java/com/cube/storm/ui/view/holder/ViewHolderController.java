package com.cube.storm.ui.view.holder;

import android.view.ViewGroup;

import lombok.Getter;

/**
 * Holder class which is used for populating the list. This class should be treated like any other
 * view holder, and should keep references of the views it needs to popuate within the class, and
 * referenced in {@link #populateView(Object)}
 *
 * @author Callum Taylor
 * @project Storm Test
 */
public abstract class ViewHolderController
{
	protected ViewHolder mViewHolder;

	public abstract void createViewHolder(ViewGroup parent);

	/**
	 * Retrieve the ViewHolder which is a member of the controller
	 * @return The ViewHolder to populate the view
	 */
	public ViewHolder getViewHolder()
	{
		return mViewHolder;
	}
}
