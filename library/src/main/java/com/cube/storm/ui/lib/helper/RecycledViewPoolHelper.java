package com.cube.storm.ui.lib.helper;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

/**
 * RecycledViewPoolHelper
 * Creates a singleton for the view holder pool in the recycler view for memory saving and sharing of views within different lists
 *
 * @author Luke Reed
 * @project LightningUi
 */
public class RecycledViewPoolHelper
{
	private static RecycledViewPoolHelper instance;
	private RecycledViewPool recycledViewPool;

	public void RecyclerViewPoolHelper()
	{
		recycledViewPool = new RecycledViewPool();
	}

	public static RecycledViewPoolHelper getInstance()
	{
		if (instance == null)
		{
			instance = new RecycledViewPoolHelper();
		}

		return instance;
	}

	public RecycledViewPool getRecycledViewPool()
	{
		return recycledViewPool;
	}
}
