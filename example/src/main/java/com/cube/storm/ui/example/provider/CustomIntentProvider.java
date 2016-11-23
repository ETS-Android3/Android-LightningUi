package com.cube.storm.ui.example.provider;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.example.CustomStormActivity;
import com.cube.storm.ui.lib.provider.IntentProvider;
import com.cube.storm.ui.model.descriptor.PageDescriptor;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 */
public class CustomIntentProvider extends IntentProvider
{
	@Nullable @Override public FragmentIntent provideFragmentIntentForPageDescriptor(@NonNull PageDescriptor pageDescriptor)
	{
		if (pageDescriptor.getSrc().equals("test://page"))
		{
			return new FragmentIntent(Fragment.class);
		}

		return null;
	}

	@Nullable @Override public Intent provideIntentForPageDescriptor(@NonNull Context context, @NonNull PageDescriptor pageDescriptor)
	{
		if (pageDescriptor.getSrc().equals("test://page"))
		{
			return new Intent(context, CustomStormActivity.class);
		}

		return null;
	}
}
