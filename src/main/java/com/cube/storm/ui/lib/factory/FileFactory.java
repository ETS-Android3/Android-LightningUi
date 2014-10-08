package com.cube.storm.ui.lib.factory;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.UiSettings;

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
	public byte[] loadFromUri(@NonNull Uri fileUri)
	{
		// Loop through the resolvers to resolve the file
		for (String protocol : UiSettings.getInstance().getUriResolvers().keySet())
		{
			if (protocol.equalsIgnoreCase(fileUri.getScheme()))
			{
				return UiSettings.getInstance().getUriResolvers().get(protocol).resolveFile(fileUri);
			}
		}

		return null;
	}
}
