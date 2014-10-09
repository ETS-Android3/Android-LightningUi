package com.cube.storm.ui.lib.factory;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.fragment.StormListFragment;
import com.cube.storm.ui.fragment.StormTabbedFragment;
import com.cube.storm.ui.model.App;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.descriptor.PageDescriptor;
import com.cube.storm.ui.model.page.ListPage;
import com.cube.storm.ui.model.page.Page;
import com.cube.storm.ui.model.page.PageCollection;
import com.cube.storm.ui.model.page.TabbedPageCollection;

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
	 * @deprecated You should not load a fragment intent from an already-instantiated page, use {@link #getFragmentIntentForPageDescriptor(com.cube.storm.ui.model.descriptor.PageDescriptor)} instead
	 *
	 * Returns a fragment intent for a specific page
	 *
	 * @param pageData The page to use to decide the fragment that is used
	 *
	 * @return The fragment intent, or null if one was not suitable enough
	 */
	@Nullable @Deprecated
	public FragmentIntent getFragmentIntentForPage(@NonNull Page pageData)
	{
		FragmentIntent intent;
		Bundle arguments = new Bundle();
		arguments.putSerializable(StormActivity.EXTRA_PAGE, pageData);

		if (pageData instanceof ListPage)
		{
			intent = new FragmentIntent(StormListFragment.class, pageData.getTitle() != null ? pageData.getTitle().getContent() : "", arguments);
			return intent;
		}

		return null;
	}

	/**
	 * @deprecated You should not load an intent from an already-instantiated page, use {@link #getIntentForPageDescriptor(android.content.Context, com.cube.storm.ui.model.descriptor.PageDescriptor)} instead
	 *
 	 * Returns an activity intent for a specific page
	 *
	 * @param pageData The page to use to decide the activity that is used
	 *
	 * @return The intent, or null if one was not suitable enough
	 */
	@Nullable @Deprecated
	public Intent getIntentForPage(@NonNull Context context, @NonNull Page pageData)
	{
		Intent intent;
		Bundle arguments = new Bundle();
		arguments.putSerializable(StormActivity.EXTRA_PAGE, pageData);

		if (pageData instanceof ListPage)
		{
			intent = new Intent(context, StormActivity.class);
			intent.putExtras(arguments);

			return intent;
		}

		return null;
	}

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
		else
		{
			Page page = UiSettings.getInstance().getViewBuilder().buildPage(pageUri);

			if (page != null)
			{
				return getFragmentIntentForPage(page);
			}
		}

		return null;
	}

	/**
	 * Loads an intent from a Uri by finding the {@link com.cube.storm.ui.model.descriptor.PageDescriptor} in the {@link com.cube.storm.ui.model.App} model defined
	 * in {@link com.cube.storm.UiSettings#getApp()}. It will load the page from disk if {@link com.cube.storm.UiSettings#getApp()} is null.
	 *
	 * @param pageUri The page uri
	 *
	 * @return The intent, or null if one was not suitable enough
	 */
	@Nullable
	public Intent geIntentForPageUri(@NonNull Context context, @NonNull Uri pageUri)
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
		else
		{
			Page page = UiSettings.getInstance().getViewBuilder().buildPage(pageUri);

			if (page != null)
			{
				return getIntentForPage(context, page);
			}
		}

		return null;
	}

	/**
	 * Loads a fragment intent from a page descriptor by finding the model of the page type defined in {@link com.cube.storm.ui.model.descriptor.PageDescriptor#getType()} in the
	 * {@link com.cube.storm.ui.view.View} enum.
	 *
	 * You should override this method to handle custom uris and cases
	 *
	 * @param pageDescriptor The page descriptor to load from
	 *
	 * @return The intent, or null if one was not suitable enough
	 */
	@Nullable
	public FragmentIntent getFragmentIntentForPageDescriptor(@NonNull PageDescriptor pageDescriptor)
	{
		FragmentIntent intent;
		Class<? extends Model> pageType = UiSettings.getInstance().getViewFactory().getModelForView(pageDescriptor.getType());

		Bundle arguments = new Bundle();
		arguments.putString(StormActivity.EXTRA_URI, pageDescriptor.getSrc());

		if (ListPage.class.isAssignableFrom(pageType))
		{
			intent = new FragmentIntent(StormListFragment.class, null, arguments);
			return intent;
		}
		else if (TabbedPageCollection.class.isAssignableFrom(pageType))
		{
			intent = new FragmentIntent(StormTabbedFragment.class, null, arguments);
			return intent;
		}

		return null;
	}

	/**
	 * Loads an intent from a page descriptor by finding the model of the page type defined in {@link com.cube.storm.ui.model.descriptor.PageDescriptor#getType()} in the
	 * {@link com.cube.storm.ui.view.View} enum.
	 *
	 * You should override this method to handle custom uris and cases
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
		Class<? extends Model> pageType = UiSettings.getInstance().getViewFactory().getModelForView(pageDescriptor.getType());

		arguments.putString(StormActivity.EXTRA_URI, pageDescriptor.getSrc());

		if (Page.class.isAssignableFrom(pageType)
		|| PageCollection.class.isAssignableFrom(pageType))
		{
			intent = new Intent(context, StormActivity.class);
			intent.putExtras(arguments);

			return intent;
		}

		return null;
	}
}
