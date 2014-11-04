package com.cube.storm.ui.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.Divider;

/**
 * Simple view holder for divider
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class DividerHolder extends Holder<Divider>
{
	@Override public View createView(ViewGroup parent)
	{
		return LayoutInflater.from(parent.getContext()).inflate(R.layout.divider, parent, false);
	}

	@Override public void populateView(Divider model)
	{
	}
}
