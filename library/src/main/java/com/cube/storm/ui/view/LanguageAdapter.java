package com.cube.storm.ui.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.cube.storm.ui.R;

import java.util.ArrayList;

/**
 * // TODO: Add class description
 *
 *
 * Created by Nikos Rapousis on 20/February/2019.
 * Copyright ® 3SidedCube. All rights reserved.
 */
public class LanguageAdapter implements SpinnerAdapter
{
	ArrayList<String> languages = new ArrayList<>();

	public LanguageAdapter(ArrayList<String> languages)
	{
		this.languages = languages;
	}

	@Override public View getDropDownView(int position, View convertView, ViewGroup parent)
	{
		View expandedView;
		LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		expandedView = inflater.inflate(R.layout.language_spinner_drop_view, null);

		((TextView)expandedView.findViewById(R.id.language_name)).setText(languages.get(position));

		return expandedView;
	}

	@Override public void registerDataSetObserver(DataSetObserver observer)
	{

	}

	@Override public void unregisterDataSetObserver(DataSetObserver observer)
	{

	}

	@Override public int getCount()
	{
		return languages.size();
	}

	@Override public Object getItem(int position)
	{
		return languages.get(position);
	}

	@Override public long getItemId(int position)
	{
		return 0;
	}

	@Override public boolean hasStableIds()
	{
		return false;
	}

	@Override public View getView(int position, View convertView, ViewGroup parent)
	{
		View expandedView;
		LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		expandedView = inflater.inflate(R.layout.language_spinner_view, null);

		((TextView)expandedView.findViewById(R.id.language_name)).setText(languages.get(position));

		return expandedView;
	}

	@Override public int getItemViewType(int position)
	{
		return 1;
	}

	@Override public int getViewTypeCount()
	{
		return 1;
	}

	@Override public boolean isEmpty()
	{
		return languages.isEmpty();
	}
}
