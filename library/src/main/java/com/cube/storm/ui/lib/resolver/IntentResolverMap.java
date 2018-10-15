package com.cube.storm.ui.lib.resolver;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.model.descriptor.PageDescriptor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Intent resolver class used before {@link com.cube.storm.ui.lib.factory.IntentFactory} to resolve any page using its ID/URI/Descriptor.
 * <p/>
 * This class should not be overridden, and accessed via {@link UiSettings#getIntentResolver()}.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class IntentResolverMap
{
	protected Map<IntentOptions, IntentResolver> internalMap = new LinkedHashMap<>();

	/**
	 * Registers a page id/name to resolve to a specific intent resolver.
	 *
	 * @param pageId The id of the page. This will also match on a page's `name`
	 * @param resolver The intent resolver class
	 */
	public void registerPageId(@NonNull String pageId, @NonNull IntentResolver resolver)
	{
		internalMap.put(new IntentOptions(pageId, null, null), resolver);
	}

	/**
	 * Registers a page URI to resolve to a specific intent resolver.
	 *
	 * @param pageUri The URI of the page.
	 * @param resolver The intent resolver class
	 */
	public void registerPageUri(@NonNull Uri pageUri, @NonNull IntentResolver resolver)
	{
		internalMap.put(new IntentOptions(null, pageUri, null), resolver);
	}

	/**
	 * Registers a page descriptor to resolve to a specific intent resolver.
	 *
	 * @param pageDescriptor The descriptor of the page.
	 * @param resolver The intent resolver class
	 */
	public void registerPageDescriptor(@NonNull PageDescriptor pageDescriptor, @NonNull IntentResolver resolver)
	{
		internalMap.put(new IntentOptions(null, null, pageDescriptor), resolver);
	}

	/**
	 * Resolves an intent resolver from a given pageId
	 *
	 * @param pageId The page ID used when registering the resolver.
	 *
	 * @return The first found intent resolver (last added), or null
	 */
	@Nullable
	public IntentResolver resolveIntentResolver(String pageId)
	{
		List<Map.Entry<IntentOptions, IntentResolver>> entries = new ArrayList<>(internalMap.entrySet());

		for (int count = entries.size(), index = count - 1; index >= 0; index--)
		{
			IntentOptions intentOptions = entries.get(index).getKey();

			if (intentOptions.getPageId() != null && pageId != null
			&& intentOptions.getPageId().equalsIgnoreCase(pageId))
			{
				return entries.get(index).getValue();
			}
		}

		return null;
	}

	/**
	 * Resolves an intent resolver from a given page uri.
	 *
	 * @param pageUri The page uri used when registering the resolver
	 *
	 * @return The first found intent resolver (last added), or null
	 */
	@Nullable
	public IntentResolver resolveIntentResolver(Uri pageUri)
	{
		List<Map.Entry<IntentOptions, IntentResolver>> entries = new ArrayList<>(internalMap.entrySet());

		for (int count = entries.size(), index = count - 1; index >= 0; index--)
		{
			IntentOptions intentOptions = entries.get(index).getKey();

			if (intentOptions.getPageUri() != null && pageUri != null
			&& intentOptions.getPageUri().equals(pageUri))
			{
				return entries.get(index).getValue();
			}
		}

		return null;
	}

	/**
	 * Resolves an intent resolver from a given page descriptor.
	 *
	 * @param pageDescriptor The page descriptor used when registering the resolver
	 *
	 * @return The first found intent resolver (last added), or null
	 */
	@Nullable
	public IntentResolver resolveIntentResolver(PageDescriptor pageDescriptor)
	{
		List<Map.Entry<IntentOptions, IntentResolver>> entries = new ArrayList<>(internalMap.entrySet());

		for (int count = entries.size(), index = count - 1; index >= 0; index--)
		{
			IntentOptions intentOptions = entries.get(index).getKey();

			if (intentOptions.getPageDescriptor() != null && pageDescriptor != null
			&& intentOptions.getPageDescriptor().getId().equalsIgnoreCase(pageDescriptor.getId()))
			{
				return entries.get(index).getValue();
			}
		}

		return null;
	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Accessors(chain = true) @Data
	private class IntentOptions
	{
		protected String pageId;
		protected Uri pageUri;
		protected PageDescriptor pageDescriptor;
	}
}
