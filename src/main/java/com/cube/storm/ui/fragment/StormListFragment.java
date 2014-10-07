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
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.controller.adapter.StormListAdapter;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.page.Page;
import com.cube.storm.ui.view.ViewClickable;

import lombok.Getter;

public class StormListFragment extends Fragment implements OnItemClickListener
{
	@Getter private ListView listView;
	@Getter private StormListAdapter adapter;
	@Getter private Page page;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.list_page_fragment_view, container, false);
		listView = (ListView)v.findViewById(android.R.id.list);

		return v;
	}

	@Override public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		adapter = new StormListAdapter(getActivity());

		if (getArguments().containsKey(StormActivity.EXTRA_PAGE))
		{
			page = (Page)getArguments().get(StormActivity.EXTRA_PAGE);

			if (page != null)
			{
				adapter.setItems(page.getChildren());
			}
		}

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Model item = (Model)listView.getItemAtPosition(position);

		if (view.getTag() != null && view.getTag() instanceof ViewClickable)
		{
			((ViewClickable)view.getTag()).onClick(item, view);
		}
	}
}
