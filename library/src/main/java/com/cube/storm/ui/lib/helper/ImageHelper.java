package com.cube.storm.ui.lib.helper;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.data.ContentSize;
import com.cube.storm.ui.model.property.ImageProperty;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project Storm
 */
public class ImageHelper
{
	public static void displayImage(@NonNull final ImageView image, @Nullable List<ImageProperty> images)
	{
		displayImage(image, images, new SimpleImageLoadingListener()
		{
			@Override public void onLoadingStarted(String imageUri, View view)
			{
				super.onLoadingStarted(imageUri, view);
			}

			@Override public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
			{
				if (loadedImage != null)
				{
					image.setVisibility(View.VISIBLE);
				}
			}

			@Override public void onLoadingFailed(String imageUri, View view, FailReason failReason)
			{

			}
		});
	}

	public static void displayImage(@NonNull ImageView image, @Nullable List<ImageProperty> images, ImageLoadingListener listener)
	{
		if (images != null)
		{
			ImageViewAware aware = new ImageViewAware(image, true);

			String src = ImageHelper.getImageSrc(images, aware.getWidth(), aware.getHeight());
			UiSettings.getInstance().getImageLoader().displayImage(src, image, listener);
		}
	}

	@Nullable
	public static String getImageSrc(@NonNull List<? extends ImageProperty> images)
	{
		return getImageSrc(images, 0, 0);
	}

	@Nullable
	public static String getImageSrc(@NonNull List<? extends ImageProperty> images, int width, int height)
	{
		ImageProperty imageProperty = getImageProperty(images, width, height);

		if (imageProperty != null)
		{
			return imageProperty.getSrc().getDestination();
		}

		return null;
	}

	@Nullable
	public static ImageProperty getImageProperty(@NonNull List<? extends ImageProperty> images)
	{
		return getImageProperty(images, 0, 0);
	}

	@Nullable
	public static ImageProperty getImageProperty(@NonNull List<? extends ImageProperty> images, int width, int height)
	{
		Collections.sort(images, new ImagePropertyComparator());

		if (images.size() == 0)
		{
			return null;
		}

		if (width == 0 && height == 0)
		{
			if (UiSettings.getInstance().getContentSize() == ContentSize.SMALL)
			{
				return images.get(0);
			}
			else if (UiSettings.getInstance().getContentSize() == ContentSize.LARGE)
			{
				return images.get(images.size() - 1);
			}

			return images.get((int)Math.max(Math.ceil((double)images.size() / 2d), images.size() - 1));
		}
		else
		{
			int closest = 0;
			for (int index = 0, count = images.size(); index < count; index++)
			{
				int imageWidth = images.get(index).getDimensions().getWidth();
				int imageHeight = images.get(index).getDimensions().getHeight();

				if (width >= imageWidth && height >= imageHeight)
				{
					closest = index;
				}
			}

			return images.get(closest);
		}
	}

	private static class ImagePropertyComparator implements Comparator<ImageProperty>
	{
		@Override public int compare(ImageProperty lhs, ImageProperty rhs)
		{
			long totalArea = lhs.getDimensions().getHeight() * lhs.getDimensions().getWidth();
			return Long.valueOf(totalArea).compareTo((long)(rhs.getDimensions().getHeight() * rhs.getDimensions().getWidth()));
		}
	}
}
