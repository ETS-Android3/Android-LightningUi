package com.cube.storm.ui.lib.factory;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.lib.resolver.AssetsResolver;
import com.cube.storm.ui.lib.resolver.FileResolver;

/**
 * Factory class used to resolve a file based on it's Uri
 *
 * @author Callum Taylor
 * @project StormUI
 */
public abstract class FileFactory
{
	/**
	 * Loads a file from disk based on its Uri location
	 *
	 * @param fileUri The file Uri to resolve
	 *
	 * @return The file byte array, nor null
	 */
	@Nullable
	public byte[] loadFromUri(@NonNull Context context, @NonNull Uri fileUri)
	{
		if ("file".equalsIgnoreCase(fileUri.getScheme()))
		{
			return new FileResolver().resolveUri(fileUri);
		}
		else if ("assets".equalsIgnoreCase(fileUri.getScheme()))
		{
			return new AssetsResolver(context).resolveUri(fileUri);
		}

		return null;
	}
}
