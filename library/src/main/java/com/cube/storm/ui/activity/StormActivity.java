package com.cube.storm.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.data.FragmentIntent;

/**
 * Base storm activity that hosts a single fragment to host any {@link com.cube.storm.ui.model.page.Page} subclass.
 *
 * @author Callum Taylor
 * @project LightingUi
 */
public class StormActivity extends AppCompatActivity implements StormInterface
{
	public static final String EXTRA_URI = "stormui.uri";

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(getLayoutResource());

		if (savedInstanceState == null)
		{
			if (getIntent().getExtras().containsKey(EXTRA_URI))
			{
				String pageUri = String.valueOf(getIntent().getExtras().get(EXTRA_URI));
				loadPage(pageUri);
			}
			else
			{
				onLoadFail();
			}
		}
	}

	@Override public int getLayoutResource()
	{
		return R.layout.activity_view;
	}

	@Override public void loadPage(String pageUri)
	{
		FragmentIntent fragmentIntent = UiSettings.getInstance().getIntentFactory().getFragmentIntentForPageUri(Uri.parse(pageUri));

		if (fragmentIntent != null)
		{
			if (fragmentIntent.getArguments() == null)
			{
				fragmentIntent.setArguments(new Bundle());
			}

			fragmentIntent.getArguments().putAll(getIntent().getExtras());

			Fragment fragment = Fragment.instantiate(this, fragmentIntent.getFragment().getName(), fragmentIntent.getArguments());
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragment).commit();

			if (!TextUtils.isEmpty(fragmentIntent.getTitle()))
			{
				setTitle(fragmentIntent.getTitle());
			}
		}
		else
		{
			onLoadFail();
		}
	}

	@Override public void onLoadFail()
	{
		Toast.makeText(this, "Failed to load page", Toast.LENGTH_SHORT).show();
		finish();
	}
}
