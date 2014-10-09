package com.cube.storm.ui.lib.resolver;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.cube.storm.ui.lib.manager.FileManager;
import com.cube.storm.util.lib.resolver.Resolver;

import java.io.IOException;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class AssetsResolver extends Resolver
{
	private Context context;

	public AssetsResolver(Context context)
	{
		this.context = context;
	}

	@Override public Uri resolveUri(Uri uri)
	{
		if ("assets".equalsIgnoreCase(uri.getScheme()))
		{
			return uri;
		}

		return null;
	}

	@Override public byte[] resolveFile(Uri uri)
	{
		String filePath = "";

		if (!TextUtils.isEmpty(uri.getHost()))
		{
			filePath += uri.getHost();
		}

		if (!TextUtils.isEmpty(uri.getPath()))
		{
			filePath += uri.getPath();
		}

		try
		{
			return FileManager.getInstance().readFile(context.getAssets().open(filePath));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
