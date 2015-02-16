package com.cube.storm.ui.view.holder.list;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.collection.AppCollectionItem;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class AppCollectionItemHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_collection_item, parent, false);
		mViewHolder = new AppCollectionItemViewHolder(view);

		return mViewHolder;
	}

	public class AppCollectionItemViewHolder extends ViewHolder<AppCollectionItem> implements OnClickListener
	{
		protected ImageView image;
		protected TextView overlay;
		protected LinkProperty link;

		public AppCollectionItemViewHolder(View view)
		{
			super(view);

			view.setOnClickListener(this);

			image = (ImageView)view.findViewById(R.id.icon);
			overlay = (TextView)view.findViewById(R.id.overlay);
		}

		@Override public void populateView(final AppCollectionItem model)
		{
			overlay.setVisibility(View.GONE);

			link = model.getLink();

			String content = UiSettings.getInstance().getTextProcessor().process(model.getOverlay().getContent());

			if (!TextUtils.isEmpty(content))
			{
				overlay.setText(content);
				overlay.setVisibility(View.VISIBLE);
			}

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

		@Override public void onClick(View v)
		{
			if (link != null)
			{
				UiSettings.getInstance().getLinkHandler().handleLink(image.getContext(), link);
			}
		}
	}
}
