package com.cube.storm.ui.lib;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.page.Page;
import com.cube.storm.ui.model.property.LinkProperty;

/**
 * Hook class used for registering for different user events within the framework
 */
public class EventHook
{
	/**
	 * Called when a view is clicked with a link
	 *
	 * @param itemView The view that was clicked
	 * @param model The model attached to the view
	 * @param link The link that was actioned
	 */
	public void onViewLinkedClicked(@NonNull View itemView, @Nullable Model model, @NonNull LinkProperty link)
	{

	}

	/**
	 * Called when a view is clicked with no link
	 *
	 * @param itemView The view that was clicked
	 * @param model The model attached to the view
	 */
	public void onViewClicked(@NonNull View itemView, @Nullable Model model)
	{

	}

	/**
	 * Called when a page is opened/resumed.
	 *
	 * @param pageContext The activity context
	 * @param fragment The fragment of the page, can be null
	 * @param page The page data
	 */
	public void onPageOpened(@NonNull Context pageContext, @Nullable Fragment fragment, @NonNull Page page)
	{

	}

	/**
	 * Called when a page is close/destroyed.
	 *
	 * @param pageContext The activity context
	 * @param fragment The fragment of the page, can be null
	 * @param page The page data
	 */
	public void onPageClosed(@NonNull Context pageContext, @Nullable Fragment fragment, @NonNull Page page)
	{

	}
}
