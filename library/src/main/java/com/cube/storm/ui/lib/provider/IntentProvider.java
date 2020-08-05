package com.cube.storm.ui.lib.provider;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.model.descriptor.PageDescriptor;

/**
 * Provider interface for handling intents
 *
 * @author Callum Taylor
 */
public abstract class IntentProvider
{
	/**
	 * Loads a fragment intent from a page descriptor by finding the model of the page type defined in {@link com.cube.storm.ui.model.descriptor.PageDescriptor#getType()}
	 * <p/>
	 * To register a specific URI or page ID, use {@link UiSettings#getIntentResolver()} instead. Order of resolve priority is
	 * `pageDescriptor` -> `pageSrc` -> `pageId` -> `pageName`
	 *
	 * @param pageDescriptor The page descriptor to load from
	 *
	 * @return The intent, or null if one was not suitable enough
	 */
	@Nullable
	public abstract FragmentIntent provideFragmentIntentForPageDescriptor(@NonNull PageDescriptor pageDescriptor);

	/**
	 * Loads an intent from a page descriptor by finding the model of the page type defined in {@link com.cube.storm.ui.model.descriptor.PageDescriptor#getType()}
	 * <p/>
	 * To register a specific URI or page ID, use {@link UiSettings#getIntentResolver()} instead. Order of resolve priority is
	 * `pageDescriptor` -> `pageSrc` -> `pageId` -> `pageName`
	 *
	 * @param context The context used to create the intent
	 * @param pageDescriptor The page descriptor to load from
	 *
	 * @return The intent, or null if one was not suitable enough
	 */
	@Nullable
	public abstract Intent provideIntentForPageDescriptor(@NonNull Context context, @NonNull PageDescriptor pageDescriptor);
}
