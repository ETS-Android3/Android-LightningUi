package com.cube.storm.ui.view;

import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * An extension of {@link android.support.v7.widget.StaggeredGridLayoutManager} that allows for
 * certain items in the grid to be the full width rather than aligning to width set for the rest of
 * the views.
 *
 * The section headers will be recognised in this class and automatically resize to be a the full width
 *
 * @author Matt Allen
 * @project Storm
 */
public class SectionedGridLayoutManager extends StaggeredGridLayoutManager
{
	public SectionedGridLayoutManager(int spanCount, int orientation)
	{
		super(spanCount, orientation);
	}
}
