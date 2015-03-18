package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.ImageListItem;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;

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
		mViewHolder = new ImageListItemViewHolder(view);

		return mViewHolder;
	}

	private static class ImageListItemViewHolder extends ViewHolder<ImageListItem>
	{
		protected ImageView image;
		protected ProgressBar progress;

		public ImageListItemViewHolder(View view)
		{
			super(view);
			image = (ImageView)view.findViewById(R.id.image);
			progress = (ProgressBar)view.findViewById(R.id.progress);
		}

		@Override public void populateView(final ImageListItem model)
		{
			image.populate(model.getImage(), progress);
		}
	}
}
