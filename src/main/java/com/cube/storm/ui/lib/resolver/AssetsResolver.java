package com.cube.storm.ui.lib.resolver;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.cube.storm.ui.lib.manager.CacheManager;

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

	@Override public byte[] resolveUri(Uri uri)
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
			return CacheManager.getInstance().readFile(context.getAssets().open(filePath));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
