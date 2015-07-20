package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.LogoListItem;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.list.LogoListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @Project LightningUi
 */
public class LogoListItemViewHolder extends ViewHolder<LogoListItem> implements View.OnClickListener
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public LogoListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.logo_list_item_view, parent, false);
			return new LogoListItemViewHolder(view);
		}
	}

	protected ImageView image;
	protected TextView linkTitle;

	public LogoListItemViewHolder(View view)
	{
		super(view);
		view.setOnClickListener(this);
		image = (ImageView)view.findViewById(R.id.image_view);
		linkTitle = (TextView)view.findViewById(R.id.link_title);
	}

	@Override public void populateView(final LogoListItem model)
	{
		image.populate(model.getImage());
		linkTitle.populate(model.getLink().getTitle(), model.getLink());
	}

	@Override public void onClick(View v)
	{
		linkTitle.callOnClick();
	}

}
