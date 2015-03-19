package com.cube.storm.ui.view.holder.grid;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.cube.storm.ui.R;
import com.cube.storm.ui.lib.helper.ImageHelper;
import com.cube.storm.ui.model.grid.ImageGridItem;
import com.cube.storm.ui.view.holder.GridViewHolder;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * View holder for {@link com.cube.storm.ui.model.grid.ImageGridItem} in the adapter
 *
 * @author Luke Reed
 * @project Storm
 */
public class ImageGridItemHolder extends ViewHolderController
{

	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_grid_cell_view, parent, false);
		mViewHolder = new ImageGridItemViewHolder(view);

		return mViewHolder;
	}

	private class ImageGridItemViewHolder extends GridViewHolder<ImageGridItem>
	{
		protected ImageView image;
		protected ProgressBar progress;

		public ImageGridItemViewHolder(View view)
		{
			super(view);

			image = (ImageView)view.findViewById(R.id.image);
			progress = (ProgressBar)view.findViewById(R.id.progress);
		}

		@Override public void populateView(final ImageGridItem model)
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
