package com.cube.storm.ui.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.controller.adapter.StormListAdapter;
import com.cube.storm.ui.lib.helper.RecycledViewPoolHelper;
import com.cube.storm.ui.model.page.Page;

import lombok.Getter;

public class StormFragment extends Fragment
{
	@Getter private RecyclerView listView;
	@Getter private StormListAdapter adapter;
	@Getter private Page page;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.list_page_fragment_view, container, false);
		listView = (RecyclerView)v.findViewById(R.id.recyclerview);
		listView.setRecycledViewPool(RecycledViewPoolHelper.getInstance().getRecycledViewPool());
		if (getArguments().getString(StormActivity.EXTRA_RECYCLERVIEW_LAYOUT).equals(StormActivity.EXTRA_RECYCLERVIEW_LAYOUT_LIST))
		{
			listView.setLayoutManager(new LinearLayoutManager(getActivity()));
		}
		else if (getArguments().getString(StormActivity.EXTRA_RECYCLERVIEW_LAYOUT).equals(StormActivity.EXTRA_RECYCLERVIEW_LAYOUT_GRID))
		{
			listView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
		}
		listView.setItemAnimator(new DefaultItemAnimator());

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
		else
		{
			Toast.makeText(getActivity(), "Failed to load page", Toast.LENGTH_SHORT).show();
			getActivity().finish();
			return;
		}

		if (page != null)
		{
			adapter.setItems(page.getChildren());
		}

		listView.setAdapter(adapter);
	}
}
