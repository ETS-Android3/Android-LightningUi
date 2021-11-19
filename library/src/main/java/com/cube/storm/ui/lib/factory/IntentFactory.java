package com.cube.storm.ui.lib.factory;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.lib.provider.IntentProvider;
import com.cube.storm.ui.lib.resolver.IntentResolver;
import com.cube.storm.ui.lib.resolver.ViewResolver;
import com.cube.storm.ui.model.App;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.descriptor.PageDescriptor;

/**
 * Factory class used to provide fragment/activity intents. This class shouldn't be overridden unles you wish to change
 * the way the factory resolves intents.
 *
 * The main methods are {@link #getFragmentIntentForPageDescriptor(PageDescriptor)} and {@link #getIntentForPageDescriptor(Context, PageDescriptor)}. The two methods
 * {@link #getFragmentIntentForPageUri(Uri)} and {@link #getIntentForPageUri(Context, Uri)} loop through {@link UiSettings#getIntentProviders()} to resolve the intents.
 *
 * In the main methods, firstly, {@link UiSettings#intentResolver} are checked for matching page Uri/id/descriptors, then move on to
 * checking {@link UiSettings#getIntentProviders()} to resolve the intents.
 *
 * This class does not fully resolve intents itself, only queries the aforementioned data stores. Actual functionality is
 * implemented by {@link com.cube.storm.ui.lib.provider.DefaultIntentProvider} by default.
 *
 * @author Callum Taylor
 */
public class IntentFactory
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
	@Nullable public FragmentIntent getFragmentIntentForPageUri(@NonNull Uri pageUri)
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
	@Nullable public Intent getIntentForPageUri(@NonNull Context context, @NonNull Uri pageUri)
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
	 * Firstly loops through the {@link UiSettings#intentResolver} to resolve intent, if nothing was consumed, loops through {@link UiSettings#intentProviders}
	 * until consumed, or returns null.
	 * @param pageDescriptor The page descriptor to load from
	 *
	 * @return
	 */
	@Nullable public FragmentIntent getFragmentIntentForPageDescriptor(@NonNull PageDescriptor pageDescriptor)
	{
		FragmentIntent intent = null;
		ViewResolver viewResolver = UiSettings.getInstance().getViewResolvers().get(pageDescriptor.getType());
		Class<? extends Model> pageType = null;

		if (viewResolver != null)
		{
			pageType = viewResolver.resolveModel();
		}

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
					break;
				}
			}
		}

		// If no intent was resolved, try the intent providers
		if (intent == null)
		{
			for (IntentProvider intentProvider : UiSettings.getInstance().getIntentProviders())
			{
				intent = intentProvider.provideFragmentIntentForPageDescriptor(pageDescriptor);

				if (intent != null)
				{
					break;
				}
			}
		}

		if (intent != null)
		{
			intent.getArguments().putString(StormActivity.EXTRA_URI, pageDescriptor.getSrc());
			return intent;
		}

		return null;
	}

	/**
	 * Firstly loops through the {@link UiSettings#intentResolver} to resolve intent, if nothing was consumed, loops through {@link UiSettings#intentProviders}
	 * until consumed, or returns null.
	 * @param context The context used to create the intent
	 * @param pageDescriptor The page descriptor to load from
	 *
	 * @return
	 */
	@Nullable public Intent getIntentForPageDescriptor(@NonNull Context context, @NonNull PageDescriptor pageDescriptor)
	{
		Intent intent = null;
		ViewResolver viewResolver = UiSettings.getInstance().getViewResolvers().get(pageDescriptor.getType());
		Class<? extends Model> pageType = null;

		if (viewResolver != null)
		{
			pageType = viewResolver.resolveModel();
		}

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
					break;
				}
			}
		}

		// If no intent was resolved, try the intent providers
		if (intent == null)
		{
			for (IntentProvider intentProvider : UiSettings.getInstance().getIntentProviders())
			{
				intent = intentProvider.provideIntentForPageDescriptor(context, pageDescriptor);

				if (intent != null)
				{
					break;
				}
			}
		}

		if (intent != null)
		{
			intent.putExtra(StormActivity.EXTRA_URI, pageDescriptor.getSrc());
			return intent;
		}

		return null;
	}
}
