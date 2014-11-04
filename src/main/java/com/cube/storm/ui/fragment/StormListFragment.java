package com.cube.storm.ui.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.controller.adapter.StormListAdapter;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.page.Page;
import com.cube.storm.ui.view.ViewClickable;

import lombok.Getter;

public class StormListFragment extends Fragment implements OnItemTouchListener
{
	@Getter private RecyclerView listView;
	@Getter private StormListAdapter adapter;
	@Getter private Page page;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.list_page_fragment_view, container, false);
		listView = (RecyclerView)v.findViewById(android.R.id.list);

		return v;
	}

	@Override public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		adapter = new StormListAdapter(getActivity());

		if (getArguments().containsKey(StormActivity.EXTRA_PAGE))
		{
			page = (Page)getArguments().get(StormActivity.EXTRA_PAGE);
		}
		else if (getArguments().containsKey(StormActivity.EXTRA_URI))
		{
			String pageUri = getArguments().getString(StormActivity.EXTRA_URI);
			page = UiSettings.getInstance().getViewBuilder().buildPage(Uri.parse(pageUri));
		}

		if (page != null)
		{
			adapter.setItems(page.getChildren());
		}

		listView.setAdapter(adapter);
		listView.addOnItemTouchListener(this);
	}

	@Override public void onItemTouch(AdapterView<?> parent, View view, int position, long id)
	{
		ViewHolder item = (ViewHolder)listView.findViewHolderForPosition(position);

		if (view.getTag() != null && view.getTag() instanceof ViewClickable)
		{
			((ViewClickable)view.getTag()).onClick(item, view);
		}
	}
}
