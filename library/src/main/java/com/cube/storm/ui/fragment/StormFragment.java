package com.cube.storm.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;
import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.activity.StormInterface;
import com.cube.storm.ui.controller.adapter.StormListAdapter;
import com.cube.storm.ui.lib.EventHook;
import com.cube.storm.ui.lib.helper.RecycledViewPoolHelper;
import com.cube.storm.ui.model.page.GridPage;
import com.cube.storm.ui.model.page.ListPage;
import com.cube.storm.ui.model.page.Page;

import lombok.Getter;

/**
 * Base storm fragment that hosts a {@link ListPage} or {@link GridPage}
 *
 * @author Callum Taylor
 * @project LightingUi
 */
public class StormFragment extends Fragment implements StormInterface
{
	@Getter protected RecyclerView recyclerView;
	@Getter protected StormListAdapter adapter;
	@Getter protected Page page;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(getLayoutResource(), container, false);
		recyclerView = (RecyclerView)v.findViewById(R.id.recyclerview);
		recyclerView.setRecycledViewPool(RecycledViewPoolHelper.getInstance().getRecycledViewPool());
		recyclerView.setItemAnimator(new DefaultItemAnimator());

		return v;
	}

	@Override public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		try
		{
			adapter = UiSettings.getInstance().getViewAdapter().newInstance();
		}
		catch (Exception e)
		{
			throw new RuntimeException("Could not instantiate class " + UiSettings.getInstance().getViewAdapter() + " for adapter");
		}

		if (savedInstanceState != null)
		{
			if (savedInstanceState.containsKey("page"))
			{
				page = (Page)savedInstanceState.get("page");
			}

			if (savedInstanceState.containsKey("adapter"))
			{
				adapter.restoreState((StormListAdapter.AdapterState)savedInstanceState.getSerializable("adapter"));
			}
		}

		if (page == null)
		{
			if (getArguments().containsKey(StormActivity.EXTRA_URI))
			{
				String pageUri = getArguments().getString(StormActivity.EXTRA_URI);
				loadPage(pageUri);
			}
			else
			{
				onLoadFail();
			}
		}
		else
		{
			setAdapter();
			setTitle();
		}
	}

	public void setAdapter()
	{
		if (page instanceof ListPage)
		{
			recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
			adapter.setItems(page.getChildren());
		}
		else if (page instanceof GridPage)
		{
			StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
			layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
			recyclerView.setLayoutManager(layoutManager);
			adapter.setItems(((GridPage)page).getGrid().getChildren());
		}

		recyclerView.setAdapter(adapter);
	}

	public void setTitle()
	{
		// Only set the title of the parent activity if this fragment is a direct child of that activity
		boolean isDirectChildOfActivity = getParentFragment() == null;

		if (isDirectChildOfActivity && page.getTitle() != null)
		{
			String title = UiSettings.getInstance().getTextProcessor().process(getPage().getTitle());

			if (!TextUtils.isEmpty(title))
			{
				getActivity().setTitle(title);
			}
		}
	}

	@Override public int getLayoutResource()
	{
		return R.layout.list_page_fragment_view;
	}

	@Override public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);

		if (adapter != null)
		{
			outState.putSerializable("adapter", adapter.saveState());
		}

		if (page != null)
		{
			outState.putSerializable("page", page);
		}
	}

	@Override public void loadPage(String pageUri)
	{
		page = UiSettings.getInstance().getViewBuilder().buildPage(Uri.parse(pageUri));

		if (page != null)
		{
			setAdapter();
			setTitle();
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

	@Override public void onResume()
	{
		super.onResume();

		if (getUserVisibleHint())
		{
			onPageOpened();
		}
	}

	/**
	 * Called automatically from {@link #onStart()} for single fragments, or from {@link ViewPager.OnPageChangeListener#onPageSelected(int)}
	 */
	public void onPageOpened()
	{
		for (EventHook eventHook : UiSettings.getInstance().getEventHooks())
		{
			if (page != null)
			{
				eventHook.onPageOpened(getActivity(), this, page);
			}
		}
	}

	@Override public void onDestroy()
	{
		if (getUserVisibleHint())
		{
			for (EventHook eventHook : UiSettings.getInstance().getEventHooks())
			{
				if (page != null)
				{
					eventHook.onPageClosed(getActivity(), this, page);
				}
			}
		}

		super.onDestroy();
	}
}
