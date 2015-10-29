package com.cube.storm.ui.lib.resolver;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.util.lib.resolver.Resolver;

import java.io.ByteArrayOutputStream;

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

	@Nullable @Override public byte[] resolveFile(@NonNull Uri uri)
	{
		if ("app".equalsIgnoreCase(uri.getHost()))
		{
			uri = resolveUri(uri);
		}

		if (uri != null)
		{
			Drawable drawable = applicationContext.getResources().getDrawable(Integer.parseInt(uri.toString().substring("drawable://".length())));

			if (drawable != null)
			{
				Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

				return stream.toByteArray();
			}
		}

		return null;
	}
}
