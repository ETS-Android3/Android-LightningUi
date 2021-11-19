package com.cube.storm.ui.view;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;

public class AdapterLinearLayout extends LinearLayout
{
	private RecyclerView.Adapter mAdapter;
	private OnItemClickListener mOnClickListener;

	public AdapterLinearLayout(Context context)
	{
		super(context);
	}

	public AdapterLinearLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public AdapterLinearLayout(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public void setAdapter(RecyclerView.Adapter adapter)
	{
		this.mAdapter = adapter;
	}

	public RecyclerView.Adapter getAdapter()
	{
		return mAdapter;
	}

	public void notifyDataSetChanged()
	{
		removeAllViewsInLayout();

		if (mAdapter != null)
		{
			int count = mAdapter.getItemCount();
			for (int index = 0; index < count; index++)
			{
				RecyclerView.ViewHolder toAdd = mAdapter.createViewHolder(this, mAdapter.getItemViewType(index));
				mAdapter.bindViewHolder(toAdd, index);

				if (toAdd != null)
				{
					addView(toAdd.itemView);
				}
			}
		}
	}
}
