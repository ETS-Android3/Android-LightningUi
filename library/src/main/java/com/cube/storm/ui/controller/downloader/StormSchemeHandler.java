package com.cube.storm.ui.controller.downloader;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.cube.storm.UiSettings;
import com.nostra13.universalimageloader.core.download.handlers.SchemeHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
		try
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
				return new FileInputStream(new File(imageUri));
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
