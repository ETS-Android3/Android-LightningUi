package com.cube.storm.ui.lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.page.Page;
import com.cube.storm.ui.model.property.LinkProperty;

/**
 * Hook class used for registering for different user events within the framework
 */
public class EventHook
{
	public void onViewLinkedClicked(View itemView, Model view, LinkProperty link)
	{

	}

	public void onViewClicked(View itemView, Model view)
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
