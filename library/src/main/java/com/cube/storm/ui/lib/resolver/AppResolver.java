package com.cube.storm.ui.lib.resolver;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.util.lib.resolver.Resolver;

import java.io.InputStream;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class AppResolver extends Resolver
{
	protected Context applicationContext;

	public AppResolver(Context context)
	{
		this.applicationContext = context.getApplicationContext();
	}

	@Nullable @Override public Uri resolveUri(@NonNull Uri uri)
	{
		if ("app".equalsIgnoreCase(uri.getScheme()) && "native".equalsIgnoreCase(uri.getHost()))
		{
			String[] uriParts = uri.toString().substring("app://native/".length()).split("/");

			String fileName = uriParts[uriParts.length - 1];
			int pos = fileName.lastIndexOf("build/intermediates/exploded-aar/com.3sidedcube.storm/exoplayer/1.0.1/res");

			if (pos > -1)
			{
				fileName = fileName.substring(0, pos);
			}

			fileName = fileName.toLowerCase().replaceAll("[^a-z_]", "");
			int res = applicationContext.getResources().getIdentifier(fileName, "build/intermediates/exploded-aar/com.android.support/appcompat-v7/22.2.0/res/drawable", applicationContext.getPackageName());

			if (res > 0)
			{
				return Uri.parse("drawable://" + res);
			}
		}

		return null;
	}

	@Nullable @Override public InputStream resolveFile(@NonNull Uri uri)
	{
		if ("app".equalsIgnoreCase(uri.getHost()))
		{
			uri = resolveUri(uri);
		}

		if (uri != null)
		{
			int id = Integer.parseInt(uri.toString().substring("drawable://".length()));

			if (id > 0)
			{
				return applicationContext.getResources().openRawResource(id);
			}
		}

		return null;
	}
}
