package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.collection.AppCollectionItem;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class AppCollectionItemViewHolder extends ViewHolder<AppCollectionItem> implements View.OnClickListener
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public AppCollectionItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_collection_item, parent, false);
			return new AppCollectionItemViewHolder(view);
		}
	}

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
		image.populate(model.getIcon());
		overlay.populate(model.getOverlay());
		link = model.getLink();
	}

	@Override public void onClick(View view)
	{
		if (link != null)
		{
			UiSettings.getInstance().getLinkHandler().handleLink(view.getContext(), link);
		}
	}

}
