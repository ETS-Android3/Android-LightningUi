package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.ImageListItem;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.list.ImageListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class ImageListItemViewHolder extends ViewHolder<ImageListItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public ImageListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item_view, parent, false);
			return new ImageListItemViewHolder(view);
		}
	}

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
		image.populate(model.getImage(), model.getAccessibilityLabel(), progress);
	}
}
