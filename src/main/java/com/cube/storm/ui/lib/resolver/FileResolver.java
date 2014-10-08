package com.cube.storm.ui.lib.resolver;

import android.net.Uri;

import com.cube.storm.ui.lib.manager.FileManager;
import com.cube.storm.util.lib.resolver.Resolver;

import java.io.File;
import java.net.URI;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class FileResolver extends Resolver
{
	@Override public Uri resolveUri(Uri uri)
	{
		if ("file".equalsIgnoreCase(uri.getScheme()))
		{
			return uri;
		}

		return null;
	}

	@Override public byte[] resolveFile(Uri uri)
	{
		return FileManager.getInstance().readFile(new File(URI.create(uri.toString())));
	}
}
