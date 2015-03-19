package com.cube.storm.ui.view.holder.list;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.cube.storm.ui.R;
import com.cube.storm.ui.lib.helper.ImageHelper;
import com.cube.storm.ui.model.list.ImageListItem;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * View holder for {@link com.cube.storm.ui.model.list.ImageListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project StormUI
 */
public class ImageListItemHolder extends ViewHolderController
{

	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item_view, parent, false);
		mViewHolder = new DescriptionListItemViewHolder(view);

		return mViewHolder;
	}

	private class DescriptionListItemViewHolder extends ViewHolder<ImageListItem>
	{
		protected ImageView image;
		protected ProgressBar progress;

		public DescriptionListItemViewHolder(View view)
		{
			super(view);
			image = (ImageView)view.findViewById(R.id.image);
			progress = (ProgressBar)view.findViewById(R.id.progress);
		}

		@Override public void populateView(final ImageListItem model)
		{
			if (model.getImage() != null)
			{
				ImageHelper.displayImage(image, model.getImage(), new SimpleImageLoadingListener()
				{
					@Override public void onLoadingStarted(String imageUri, View view)
					{
						image.setVisibility(View.INVISIBLE);
						progress.setVisibility(View.VISIBLE);
					}

					@Override public void onLoadingFailed(String imageUri, View view, FailReason failReason)
					{
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
}
