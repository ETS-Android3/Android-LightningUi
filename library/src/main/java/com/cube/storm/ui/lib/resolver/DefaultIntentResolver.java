package com.cube.storm.ui.lib.resolver;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;

import com.cube.storm.ui.data.FragmentIntent;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 */
public class DefaultIntentResolver extends IntentResolver
{
	private Class activity;
	private Class fragment;

	public DefaultIntentResolver(@Nullable Class activity, @Nullable Class fragment)
	{
		this.activity = activity;
		this.fragment = fragment;
	}

	@Override public Intent resolveIntent(Context context)
	{
		return new Intent(context, activity);
	}

	@Override public FragmentIntent resolveFragmentIntent()
	{
		return new FragmentIntent(fragment);
	}
}
