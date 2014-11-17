package com.cube.storm.ui.view.holder;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.collection.AppCollectionItem;
import com.cube.storm.ui.view.ViewClickable;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class AppCollectionItemHolder extends Holder<AppCollectionItem> implements ViewClickable<AppCollectionItem>
{
	protected ImageView image;
	protected TextView overlay;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_collection_item, parent, false);
		image = (ImageView)view.findViewById(R.id.icon);
		overlay = (TextView)view.findViewById(R.id.overlay);

		return view;
	}

	@Override public void populateView(final AppCollectionItem model)
	{
		overlay.setText(model.getOverlay().getContent());

		UiSettings.getInstance().getImageLoader().displayImage(model.getIcon().getSrc(), image, new SimpleImageLoadingListener()
		{
			@Override public void onLoadingStarted(String imageUri, View view)
			{
				image.setVisibility(View.INVISIBLE);
			}

			@Override public void onLoadingFailed(String imageUri, View view, FailReason failReason)
			{
				if (!imageUri.equalsIgnoreCase(model.getIcon().getFallbackSrc()))
				{
					UiSettings.getInstance().getImageLoader().displayImage(model.getIcon().getFallbackSrc(), image, this);
				}

				image.setVisibility(View.VISIBLE);
			}

			@Override public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
			{
				image.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override public void onClick(@NonNull AppCollectionItem model, @NonNull View view)
	{
		if (model.getLink() != null)
		{
			UiSettings.getInstance().getLinkHandler().handleLink(view.getContext(), model.getLink());
		}
	}
}
