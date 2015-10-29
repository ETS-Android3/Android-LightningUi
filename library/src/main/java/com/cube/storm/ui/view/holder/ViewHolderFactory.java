package com.cube.storm.ui.view.holder;

import android.view.ViewGroup;

/**
 * Controller for the ViewHolder that will populate the view. This will instantiate the ViewHolder
 * with the necessary view which is then retrievable with {@link ViewHolderFactory#getViewHolder()}
 *
 * @author Callum Taylor
 * @Project LightningUi Test
 */
public abstract class ViewHolderFactory
{
	public abstract ViewHolder<?> createViewHolder(ViewGroup parent);
}
