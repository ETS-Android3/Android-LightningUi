package com.cube.storm.ui.lib.factory;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.fragment.StormListFragment;
import com.cube.storm.ui.model.page.ListPage;
import com.cube.storm.ui.model.page.Page;

/**
 * This is the factory class which is used by Storm to decide which activity/fragments to instantiate
 * based on the source file's class type, name, or Uri.
 *
 * @author Callum Taylor
 * @project StormUI
 */
public abstract class IntentFactory
{
	/**
	 * Returns a fragment intent for a specific page
	 *
	 * @param pageData The page to use to decide the fragment that is used
	 *
	 * @return The fragment intent, or null if one was not suitable enough
	 */
	@Nullable
	public FragmentIntent getFragmentIntentForPage(@NonNull Page pageData)
	{
		FragmentIntent intent;

		if (pageData instanceof ListPage)
		{
			intent = new FragmentIntent(StormListFragment.class, pageData.getTitle() != null ? pageData.getTitle().getContent() : "", null);
			return intent;
		}

		return null;
	}

	/**
	 * Returns an activity intent for a specific page
	 *
	 * @param pageData The page to use to decide the activity that is used
	 *
	 * @return The intent, or null if one was not suitable enough
	 */
	@Nullable
	public Intent getIntentForPage(@NonNull Context context, @NonNull Page pageData)
	{
		Intent intent;

		if (pageData instanceof ListPage)
		{
			intent = new Intent(context, StormActivity.class);
			return intent;
		}

		return null;
	}
}
