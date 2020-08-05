package com.cube.storm.ui.lib.resolver;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;

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
	@Nullable
	public abstract Intent resolveIntent(Context context);

	@Nullable
	public abstract FragmentIntent resolveFragmentIntent();
}
