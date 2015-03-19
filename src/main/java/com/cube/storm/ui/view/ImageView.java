package com.cube.storm.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.model.property.ImageProperty;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project Storm
 */
public class ImageView extends android.widget.ImageView
{
	public ImageView(Context context)
	{
		super(context);
	}

	public ImageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public ImageView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	public ImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public void populate(final ImageProperty image)
	{
		populate(image, null);
	}

	public void populate(final ImageProperty image,
						 final ProgressBar progress)
	{
		if (image != null)
		{
			UiSettings.getInstance()
					.getImageLoader()
					.displayImage(image.getSrc(), this, new SimpleImageLoadingListener()
					{
						@Override public void onLoadingStarted(String imageUri, View view)
						{
							setVisibility(View.INVISIBLE);
							if (progress != null)
							{
								progress.setVisibility(View.VISIBLE);
							}
						}

						@Override public void onLoadingFailed(String imageUri, View view, FailReason failReason)
						{
							if (!imageUri.equalsIgnoreCase(image.getFallbackSrc()))
							{
								UiSettings.getInstance()
										.getImageLoader()
										.displayImage(image.getFallbackSrc(), ImageView.this, this);
							}

							setVisibility(View.VISIBLE);
							if (progress != null)
							{
								progress.setVisibility(View.GONE);
							}
						}

						@Override public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
						{
							setVisibility(View.VISIBLE);
							if (progress != null)
							{
								progress.setVisibility(View.GONE);
							}
						}
					});
		}
		else
		{
			setImageBitmap(null);
		}
	}
}
