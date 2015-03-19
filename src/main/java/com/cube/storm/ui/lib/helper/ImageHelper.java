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
		if (images != null)
		{
			ImageViewAware aware = new ImageViewAware(image, true);

			String src = ImageHelper.getImageSrc(images, aware.getWidth(), aware.getHeight());
			UiSettings.getInstance().getImageLoader().displayImage(src, image, new SimpleImageLoadingListener()
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
	}

	@Nullable
	public static String getImageSrc(@NonNull List<? extends ImageProperty> images)
	{
		return getImageSrc(images, 0, 0);
	}

	@Nullable
	public static String getImageSrc(@NonNull List<? extends ImageProperty> images, int width, int height)
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
				return images.get(0).getSrc().getDestination();
			}
			else if (UiSettings.getInstance().getContentSize() == ContentSize.LARGE)
			{
				return images.get(images.size() - 1).getSrc().getDestination();
			}

			return images.get((int)Math.max(Math.ceil((double)images.size() / 2d), images.size() - 1)).getSrc().getDestination();
		}
		else
		{
			int area = width * height;
			double ratio = (double)width / (double)height;
			int closest = 0;
			double bestRatio = 0;

			for (int index = 0, count = images.size(); index < count; index++)
			{
				int imageWidth = images.get(index).getDimensions().getWidth();
				int imageHeight = images.get(index).getDimensions().getHeight();

				if (imageWidth >= width && imageHeight >= height && imageWidth * imageHeight >= bestRatio)
				{
					bestRatio = imageWidth * imageHeight;
					closest = index;
				}
			}

			return images.get(closest).getSrc().getDestination();
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
