package com.cube.storm.ui.view.holder;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.ImageListItem;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * View holder for {@link com.cube.storm.ui.model.list.ImageListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project StormUI
 */
public class ImageListItemHolder extends Holder<ImageListItem>
{
	protected ImageView image;
	protected ProgressBar progress;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item_view, parent, false);
		image = (ImageView)view.findViewById(R.id.image);
		progress = (ProgressBar)view.findViewById(R.id.progress);

		return view;
	}

	@Override public void populateView(final ImageListItem model)
	{
		if (model.getImage() != null)
		{
			UiSettings.getInstance().getImageLoader().displayImage(model.getImage().getSrc(), image, new SimpleImageLoadingListener()
			{
				@Override public void onLoadingStarted(String imageUri, View view)
				{
					image.setVisibility(View.INVISIBLE);
					progress.setVisibility(View.VISIBLE);
				}

				@Override public void onLoadingFailed(String imageUri, View view, FailReason failReason)
				{
					if (!imageUri.equalsIgnoreCase(model.getImage().getFallbackSrc()))
					{
						UiSettings.getInstance().getImageLoader().displayImage(model.getImage().getFallbackSrc(), image, this);
					}

					image.setVisibility(View.VISIBLE);
					progress.setVisibility(View.GONE);
				}

				@Override public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
				{
					image.setVisibility(View.VISIBLE);
					progress.setVisibility(View.GONE);
				}
			});
		}
	}
}
