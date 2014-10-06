package com.cube.storm.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cube.storm.ui.R;

import lombok.Getter;

public class StormListFragment extends Fragment implements OnItemClickListener
{
	@Getter private ListView listView;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.list_page_fragment_view, container, false);
		listView = (ListView)v.findViewById(android.R.id.list);

		return v;
	}

	@Override public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}

	@Override public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{

	}
}
