package com.cube.storm.ui.lib.factory;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.activity.StormWebActivity;
import com.cube.storm.ui.activity.VideoPlayerActivity;
import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.fragment.StormFragment;
import com.cube.storm.ui.fragment.StormTabbedFragment;
import com.cube.storm.ui.lib.resolver.IntentResolver;
import com.cube.storm.ui.model.App;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.descriptor.PageDescriptor;
import com.cube.storm.ui.model.descriptor.VideoPageDescriptor;
import com.cube.storm.ui.model.descriptor.WebPageDescriptor;
import com.cube.storm.ui.model.page.GridPage;
import com.cube.storm.ui.model.page.ListPage;
import com.cube.storm.ui.model.page.Page;
import com.cube.storm.ui.model.page.PageCollection;
import com.cube.storm.ui.model.page.TabbedPageCollection;

/**
 * This is the factory class which is used by Storm to decide which activity/fragments to instantiate
 * based on the source file's class type, name, or Uri.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public abstract class IntentFactory
{
	/**
	 * Loads a fragment intent from a Uri by finding the {@link com.cube.storm.ui.model.descriptor.PageDescriptor} in the {@link com.cube.storm.ui.model.App} model defined
	 * in {@link com.cube.storm.UiSettings#getApp()}. It will load the page from disk if {@link com.cube.storm.UiSettings#getApp()} is null.
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
			for (PageDescriptor pageDescriptor : app.getMap())
			{
				if (pageUri.toString().equalsIgnoreCase(pageDescriptor.getSrc()))
				{
					return getFragmentIntentForPageDescriptor(pageDescriptor);
				}
			}
		}

		return null;
	}

	/**
	 * Loads an intent from a Uri by finding the {@link com.cube.storm.ui.model.descriptor.PageDescriptor} in the {@link com.cube.storm.ui.model.App} model defined
	 * in {@link com.cube.storm.UiSettings#getApp()}.
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
			for (PageDescriptor pageDescriptor : app.getMap())
			{
				if (pageUri.toString().equalsIgnoreCase(pageDescriptor.getSrc()))
				{
					return getIntentForPageDescriptor(context, pageDescriptor);
				}
			}
		}

		return null;
	}

	/**
	 * Loads a fragment intent from a page descriptor by finding the model of the page type defined in {@link com.cube.storm.ui.model.descriptor.PageDescriptor#getType()} in the
	 * {@link com.cube.storm.ui.view.View} enum.
	 * <p/>
	 * To register a specific URI or page ID, use {@link UiSettings#getIntentResolver()} instead. Order of resolve priority is
	 * `pageDescriptor` -> `pageSrc` -> `pageId` -> `pageName`
	 *
	 * @param pageDescriptor The page descriptor to load from
	 *
	 * @return The intent, or null if one was not suitable enough
	 */
	@Nullable
	public FragmentIntent getFragmentIntentForPageDescriptor(@NonNull PageDescriptor pageDescriptor)
	{
		FragmentIntent intent;
		Bundle arguments = new Bundle();
		Class<? extends Model> pageType = UiSettings.getInstance().getViewResolvers().get(pageDescriptor.getType()).resolveModel();

		arguments.putString(StormActivity.EXTRA_URI, pageDescriptor.getSrc());

		// Check intent resolver
		IntentResolver[] resolvers =
		{
			UiSettings.getInstance().getIntentResolver().resolveIntentResolver(pageDescriptor),
			UiSettings.getInstance().getIntentResolver().resolveIntentResolver(Uri.parse(pageDescriptor.getSrc())),
			UiSettings.getInstance().getIntentResolver().resolveIntentResolver(pageDescriptor.getId()),
			UiSettings.getInstance().getIntentResolver().resolveIntentResolver(pageDescriptor.getName())
		};

		for (IntentResolver resolver : resolvers)
		{
			if (resolver != null)
			{
				intent = resolver.resolveFragmentIntent();

				if (intent != null)
				{
					if (intent.getArguments() != null)
					{
						intent.getArguments().putAll(arguments);
					}
					else
					{
						intent.setArguments(arguments);
					}

					return intent;
				}
			}
		}

		// Fallback to default resolution
		if (pageType != null)
		{
			if (ListPage.class.isAssignableFrom(pageType) || GridPage.class.isAssignableFrom(pageType))
			{
				intent = new FragmentIntent(StormFragment.class, null, arguments);
				return intent;
			}
			else if (TabbedPageCollection.class.isAssignableFrom(pageType))
			{
				intent = new FragmentIntent(StormTabbedFragment.class, null, arguments);
				return intent;
			}
		}

		return null;
	}

	/**
	 * Loads an intent from a page descriptor by finding the model of the page type defined in {@link com.cube.storm.ui.model.descriptor.PageDescriptor#getType()} in the
	 * {@link com.cube.storm.ui.view.View} enum.
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
	public Intent getIntentForPageDescriptor(@NonNull Context context, @NonNull PageDescriptor pageDescriptor)
	{
		Intent intent;
		Bundle arguments = new Bundle();
		Class<? extends Model> pageType = UiSettings.getInstance().getViewResolvers().get(pageDescriptor.getType()).resolveModel();

		arguments.putString(StormActivity.EXTRA_URI, pageDescriptor.getSrc());

		// Check intent resolver
		IntentResolver[] resolvers =
		{
			UiSettings.getInstance().getIntentResolver().resolveIntentResolver(pageDescriptor),
			UiSettings.getInstance().getIntentResolver().resolveIntentResolver(Uri.parse(pageDescriptor.getSrc())),
			UiSettings.getInstance().getIntentResolver().resolveIntentResolver(pageDescriptor.getId()),
			UiSettings.getInstance().getIntentResolver().resolveIntentResolver(pageDescriptor.getName())
		};

		for (IntentResolver resolver : resolvers)
		{
			if (resolver != null)
			{
				intent = resolver.resolveIntent(context);

				if (intent != null)
				{
					intent.putExtras(arguments);
					return intent;
				}
			}
		}

		// Fallback to default resolution
		if (pageDescriptor instanceof VideoPageDescriptor
		|| UiSettings.getInstance().getLinkHandler().isYoutubeVideo(Uri.parse(pageDescriptor.getSrc()))
		|| UiSettings.getInstance().getLinkHandler().isVideo(Uri.parse(pageDescriptor.getSrc())))
		{
			intent = new Intent(context, VideoPlayerActivity.class);
			intent.putExtras(arguments);

			return intent;
		}
		else if (pageDescriptor instanceof WebPageDescriptor)
		{
			intent = new Intent(context, StormWebActivity.class);
			intent.putExtras(arguments);
			intent.putExtra(StormWebActivity.EXTRA_FILE_NAME, pageDescriptor.getSrc());

			return intent;
		}
		else if (pageType != null && (Page.class.isAssignableFrom(pageType) || PageCollection.class.isAssignableFrom(pageType)))
		{
			intent = new Intent(context, StormActivity.class);
			intent.putExtras(arguments);

			return intent;
		}

		return null;
	}
}
