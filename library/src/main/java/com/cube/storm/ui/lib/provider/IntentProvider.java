package com.cube.storm.ui.lib.provider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.model.App;
import com.cube.storm.ui.model.descriptor.PageDescriptor;

/**
 * Provider interface for handling intents
 *
 * @author Callum Taylor
 */
public abstract class IntentProvider
{
	/**
	 * Loads a fragment intent from a Uri by finding the {@link com.cube.storm.ui.model.descriptor.PageDescriptor} in the {@link com.cube.storm.ui.model.App} model defined
	 * in {@link com.cube.storm.UiSettings#getApp()}. It will load the page from disk if {@link com.cube.storm.UiSettings#getApp()} is null.
	 * If no page descriptor is found, an empty one will be created with {@link PageDescriptor#src} set to {@param pageUri}
	 *
	 * @param pageUri The page uri
	 *
	 * @return The intent, or null if one was not suitable enough
	 */
	@Nullable
	public FragmentIntent getFragmentIntentForPageUri(@NonNull Uri pageUri)
	{
		App app = UiSettings.getInstance().getApp();

		if (app != null)
		{
			PageDescriptor descriptor = app.findPageDescriptor(pageUri);

			if (descriptor == null)
			{
				descriptor = app.findPageDescriptor(pageUri.getLastPathSegment());
			}

			if (descriptor != null)
			{
				return getFragmentIntentForPageDescriptor(descriptor);
			}
		}

		return getFragmentIntentForPageDescriptor(new PageDescriptor("", "", pageUri.toString(), "", false));
	}

	/**
	 * Loads an intent from a Uri by finding the {@link com.cube.storm.ui.model.descriptor.PageDescriptor} in the {@link com.cube.storm.ui.model.App} model defined
	 * in {@link com.cube.storm.UiSettings#getApp()}. If no page descriptor is found, an empty one will be created with {@link PageDescriptor#src} set to {@param pageUri}
	 *
	 * @param pageUri The page uri
	 *
	 * @return The intent, or null if one was not suitable enough
	 */
	@Nullable
	public Intent getIntentForPageUri(@NonNull Context context, @NonNull Uri pageUri)
	{
		App app = UiSettings.getInstance().getApp();

		if (app != null)
		{
			PageDescriptor descriptor = app.findPageDescriptor(pageUri);

			if (descriptor == null)
			{
				descriptor = app.findPageDescriptor(pageUri.getLastPathSegment());
			}

			if (descriptor != null)
			{
				return getIntentForPageDescriptor(context, descriptor);
			}
		}

		return getIntentForPageDescriptor(context, new PageDescriptor("", "", pageUri.toString(), "", false));
	}

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
	public abstract FragmentIntent getFragmentIntentForPageDescriptor(@NonNull PageDescriptor pageDescriptor);

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
	public abstract Intent getIntentForPageDescriptor(@NonNull Context context, @NonNull PageDescriptor pageDescriptor);
}
