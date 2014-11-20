package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.Divider;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;

/**
 * Simple view holder for divider
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class DividerHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.divider, parent, false);
		mViewHolder = new DividerViewHolder(view);

		return mViewHolder;
	}

	private class DividerViewHolder extends ViewHolder<Divider>
	{
		public DividerViewHolder(View view)
		{
			super(view);
		}

		// No view to populate
		@Override public void populateView(Divider model){}
	}
}
