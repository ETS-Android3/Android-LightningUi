package com.cube.storm.ui.view.holder.list;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.LogoListItem;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * View holder for {@link com.cube.storm.ui.model.list.LogoListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class LogoListItemHolder extends ViewHolderController
{

	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.logo_list_item_view, parent, false);
		mViewHolder =  new LogoListItemViewHolder(view);

		return mViewHolder;
	}

	private class LogoListItemViewHolder extends ViewHolder<LogoListItem>
	{
		protected ImageView image;
		protected TextView linkTitle;

		public LogoListItemViewHolder(View view)
		{
			super(view);

			image = (ImageView)view.findViewById(R.id.image_view);
			linkTitle = (TextView)view.findViewById(R.id.link_title);
		}

		@Override public void populateView(final LogoListItem model)
		{
			if (model.getImage() != null)
			{
				UiSettings.getInstance().getImageLoader().displayImage(model.getImage().getSrc(), image, new SimpleImageLoadingListener()
				{
					@Override public void onLoadingFailed(String imageUri, View view, FailReason failReason)
					{
						UiSettings.getInstance().getImageLoader().displayImage(model.getImage().getFallbackSrc(), image);
					}
				});
			}

			if (model.getTitle() != null && !TextUtils.isEmpty(model.getTitle().getContent()))
			{
				linkTitle.setText(UiSettings.getInstance().getTextProcessor().process(model.getTitle().getContent()));
				linkTitle.setVisibility(View.VISIBLE);
			}
			else
			{
				linkTitle.setVisibility(View.GONE);
			}
		}
	}
}
