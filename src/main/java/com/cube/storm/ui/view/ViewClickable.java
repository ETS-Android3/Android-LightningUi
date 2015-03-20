package com.cube.storm.ui.view;

import android.support.annotation.NonNull;

import com.cube.storm.ui.model.Model;

/**
 * Interface to use on holders which are clickable in the list with a method to handle said click
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public interface ViewClickable<T extends Model>
{
	/**
	 * Called when the list row is clicked.
	 *
	 * @param model The model of the row in the adapter
	 * @param view The row of the list that was clicked
	 */
	public void onClick(@NonNull T model, @NonNull android.view.View view);
}
