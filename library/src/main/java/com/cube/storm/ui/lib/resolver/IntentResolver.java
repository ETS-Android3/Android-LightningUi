package com.cube.storm.ui.lib.resolver;

import android.content.Context;
import android.content.Intent;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.data.FragmentIntent;

/**
 * Class used to resolve a fragment/intent. Use this in conjunction with {@link UiSettings#getIntentResolver()} to register
 * page id/uri/descriptors for resolution
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public abstract class IntentResolver
{
	public Intent resolveIntent(Context context)
	{
		return null;
	}

	public FragmentIntent resolveFragmentIntent()
	{
		return null;
	}
}
