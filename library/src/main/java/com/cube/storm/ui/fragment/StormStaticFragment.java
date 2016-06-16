package com.cube.storm.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.activity.StormInterface;
import com.cube.storm.ui.controller.adapter.StormListAdapter;
import com.cube.storm.ui.model.page.GridPage;
import com.cube.storm.ui.model.page.ListPage;
import com.cube.storm.ui.model.page.Page;
import com.cube.storm.ui.view.AdapterLinearLayout;

import lombok.Getter;

/**
 * Static content fragment used to render an entire storm page into a single ViewGroup. Use this fragment
 * when you want to embed some storm content into a scroll view, or any other type of view as a stub.
 * <br/>
 * Currently only works with {@link com.cube.storm.ui.model.page.ListPage} pages. {@link com.cube.storm.ui.model.page.GridPage} to be added.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class StormStaticFragment extends Fragment implements StormInterface
{
	@Getter protected AdapterLinearLayout adapterView;
	@Getter protected StormListAdapter adapter;
	@Getter protected Page page;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(getLayoutResource(), container, false);
		adapterView = (AdapterLinearLayout)v.findViewById(R.id.adapterview);

		return v;
	}

	@Override public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		adapter = new StormListAdapter();

		if (getArguments().containsKey(StormActivity.EXTRA_URI))
		{
			String pageUri = getArguments().getString(StormActivity.EXTRA_URI);
			loadPage(pageUri);
		}
		else
		{
			onLoadFail();
			return;
		}
	}

	@Override public int getLayoutResource()
	{
		return R.layout.static_page_fragment_view;
	}

	@Override public void loadPage(String pageUri)
	{
		page = UiSettings.getInstance().getViewBuilder().buildPage(Uri.parse(pageUri));

		if (page != null)
		{
			if (page instanceof ListPage)
			{
				adapter.setItems(page.getChildren());
			}
			else if (page instanceof GridPage)
			{
				adapter.setItems(((GridPage)page).getGrid().getChildren());
			}

			adapterView.setAdapter(adapter);
			adapterView.notifyDataSetChanged();
		}
		else
		{
			onLoadFail();
		}
	}

	@Override public void onLoadFail()
	{
		Toast.makeText(getActivity(), "Failed to load page", Toast.LENGTH_SHORT).show();
		getActivity().finish();
	}
}
