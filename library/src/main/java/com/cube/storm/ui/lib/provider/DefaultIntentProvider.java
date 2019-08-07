package com.cube.storm.ui.lib.provider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.cube.storm.UiSettings;
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.activity.StormWebActivity;
import com.cube.storm.ui.activity.VideoPlayerActivity;
import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.fragment.StormBottomTabsFragment;
import com.cube.storm.ui.fragment.StormFragment;
import com.cube.storm.ui.lib.handler.LinkHandler;
import com.cube.storm.ui.lib.resolver.ViewResolver;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.descriptor.PageDescriptor;
import com.cube.storm.ui.model.descriptor.VideoPageDescriptor;
import com.cube.storm.ui.model.descriptor.WebPageDescriptor;
import com.cube.storm.ui.model.page.GridPage;
import com.cube.storm.ui.model.page.ListPage;
import com.cube.storm.ui.model.page.PageCollection;
import com.cube.storm.ui.model.page.TabbedPageCollection;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the factory class which is used by Storm to decide which activity/fragments to instantiate
 * based on the source file's class type, name, or Uri.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class DefaultIntentProvider extends IntentProvider
{
	public static Pattern YOUTUBE_VIDEO_ID_REGEX = Pattern.compile("v=([^&]+)");

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
	@Override @Nullable
	public FragmentIntent provideFragmentIntentForPageDescriptor(@NonNull PageDescriptor pageDescriptor)
	{
		FragmentIntent intent = null;
		ViewResolver viewResolver = UiSettings.getInstance().getViewResolvers().get(pageDescriptor.getType());
		Class<? extends Model> pageType = null;

		if (viewResolver != null)
		{
			pageType = viewResolver.resolveModel();
		}

		// Fallback to default resolution
		if (pageType != null)
		{
			if (ListPage.class == pageType || GridPage.class == pageType)
			{
				intent = new FragmentIntent(StormFragment.class);
			}
			else if (TabbedPageCollection.class == pageType)
			{
				intent = new FragmentIntent(StormBottomTabsFragment.class);
			}
		}

		return intent;
	}

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
	@Override @Nullable
	public Intent provideIntentForPageDescriptor(@NonNull Context context, @NonNull PageDescriptor pageDescriptor)
	{
		Intent intent = null;
		ViewResolver viewResolver = UiSettings.getInstance().getViewResolvers().get(pageDescriptor.getType());
		Class<? extends Model> pageType = null;

		if (viewResolver != null)
		{
			pageType = viewResolver.resolveModel();
		}

		// Fallback to default resolution
		if (pageDescriptor instanceof VideoPageDescriptor
		    || LinkHandler.isYoutubeVideo(Uri.parse(pageDescriptor.getSrc()))
		    || LinkHandler.isVideo(Uri.parse(pageDescriptor.getSrc())))
		{
			if (LinkHandler.isYoutubeVideo(Uri.parse(pageDescriptor.getSrc())) && context instanceof Activity)
			{
				String youtubeApiKey = UiSettings.getInstance().getYoutubeApiKey();
				if (youtubeApiKey != null)
				{
					Matcher videoIdMatcher = YOUTUBE_VIDEO_ID_REGEX.matcher(pageDescriptor.getSrc());
					if (videoIdMatcher.find())
					{
						String videoId = videoIdMatcher.group(1);
						intent = YouTubeStandalonePlayer.createVideoIntent((Activity)context, youtubeApiKey, videoId, 0, true, false);

					}

					if (intent == null || context.getPackageManager().resolveActivity(intent, 0) == null)
					{
						intent = new Intent(Intent.ACTION_VIEW);
						intent.setData(Uri.parse(pageDescriptor.getSrc()));
					}
				}
			}

			if (intent == null)
			{
				intent = new Intent(context, VideoPlayerActivity.class);
			}
		}
		else if (pageDescriptor instanceof WebPageDescriptor)
		{
			intent = new Intent(context, StormWebActivity.class);
			intent.putExtra(StormWebActivity.EXTRA_FILE_NAME, pageDescriptor.getSrc());
		}
		else if (pageType != null && (ListPage.class == pageType || GridPage.class == pageType || PageCollection.class == pageType || TabbedPageCollection.class == pageType))
		{
			intent = new Intent(context, StormActivity.class);
		}

		return intent;
	}
}
