package com.cube.storm.ui.view.holder;

import android.view.View;
import android.view.ViewGroup;

/**
 * Holder class which is used for populating the list. This class should be treated like any other
 * view holder, and should keep references of the views it needs to popuate within the class, and
 * referenced in {@link #populateView(Object)}
 *
 * @author Callum Taylor
 * @project Storm Test
 */
public abstract class Holder<T>
{
	/**
	 * Method called when a new view needs to be created in the adapter.
	 *
	 * @param parent The parent view. Use this with {@link android.view.LayoutInflater} to inflate your view
	 *
	 * @return The created view
	 */
	public abstract View createView(ViewGroup parent);

	/**
	 * Called when the view needs to be populated
	 *
	 * @param model The model to populate the view with
	 */
	public abstract void populateView(T model);
}
