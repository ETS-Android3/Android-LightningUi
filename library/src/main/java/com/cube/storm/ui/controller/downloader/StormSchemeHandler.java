package com.cube.storm.ui.controller.downloader;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.cube.storm.UiSettings;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.handlers.SchemeHandler;

import java.io.InputStream;
import java.util.Map;

/**
 * TODO Class doc
 *
 * @author Matt Allen
 */
public class StormSchemeHandler extends SchemeHandler
{
	@Override
	public InputStream getStreamForPath(Context context, String path, Object optionForDownloader, int connectTimeout, int readTimeout)
	{
		String scheme = Uri.parse(path).getScheme();
		String imageUri = null;

		if (!TextUtils.isEmpty(scheme))
		{
			for (String protocol : UiSettings.getInstance().getUriResolvers().keySet())
			{
				if (protocol.equalsIgnoreCase(scheme))
				{
					imageUri = UiSettings.getInstance().getUriResolvers().get(protocol).resolveUri(path).toString();
				}
			}
		}
		if (!TextUtils.isEmpty(imageUri))
		{
			Map<String, SchemeHandler> handlers = ImageLoader.getInstance().getRegisteredSchemeHandlers();
			for (String protocol : handlers.keySet())
			{
				String actualScheme = Uri.parse(imageUri).getScheme();
				if (protocol.equalsIgnoreCase(actualScheme) && !(handlers.get(protocol) instanceof StormSchemeHandler))
				{
					return handlers.get(protocol).getStreamForPath(context, imageUri, optionForDownloader, connectTimeout, readTimeout);
				}
			}
		}
		return null;
	}
}
