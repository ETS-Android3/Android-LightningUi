package com.cube.storm.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.model.page.Page;

/**
 * Base storm activity that hosts a single fragment to host any {@link com.cube.storm.ui.model.page.Page} subclass.
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class StormActivity extends Activity
{
	public static final String EXTRA_PAGE = "stormui.page";

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_view);

		if (getIntent().getExtras() == null)
		{
			Toast.makeText(this, "Failed to load page", Toast.LENGTH_SHORT).show();
			finish();

			return;
		}

		if (getIntent().getExtras().containsKey(EXTRA_PAGE))
		{
			Page pageData = (Page)getIntent().getExtras().get(EXTRA_PAGE);
			loadPage(pageData);
		}
	}

	protected void loadPage(Page pageData)
	{
		FragmentIntent fragmentIntent = UiSettings.getInstance().getIntentFactory().getFragmentIntentForPage(pageData);

		if (fragmentIntent != null)
		{
			if (fragmentIntent.getArguments() == null)
			{
				fragmentIntent.setArguments(new Bundle());
			}

			fragmentIntent.getArguments().putAll(getIntent().getExtras());

			Fragment fragment = Fragment.instantiate(this, fragmentIntent.getFragment().getName(), fragmentIntent.getArguments());
			getFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragment).commit();

			if (!TextUtils.isEmpty(fragmentIntent.getTitle()))
			{
				setTitle(fragmentIntent.getTitle());
			}
		}
	}
}
