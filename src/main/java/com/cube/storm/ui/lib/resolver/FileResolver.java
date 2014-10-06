package com.cube.storm.ui.lib.resolver;

import android.net.Uri;

import com.cube.storm.ui.lib.manager.CacheManager;

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
	@Override public byte[] resolveUri(Uri uri)
	{
		return CacheManager.getInstance().readFile(new File(URI.create(uri.toString())));
	}
}
