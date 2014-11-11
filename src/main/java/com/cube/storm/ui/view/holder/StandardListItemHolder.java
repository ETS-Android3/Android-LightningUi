package com.cube.storm.ui.view.holder;

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
import com.cube.storm.ui.model.list.StandardListItem;
import com.cube.storm.ui.model.property.LinkProperty;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * View holder for {@link com.cube.storm.ui.model.list.StandardListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class StandardListItemHolder extends ViewHolderController
{

	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_list_item_view, parent, false);
		mViewHolder = new StandardListItemViewHolder(view);

		return mViewHolder;
	}

	public class StandardListItemViewHolder extends ViewHolder<StandardListItem> implements OnClickListener
	{
		protected ImageView image;
		protected TextView title;
		protected TextView description;
		protected LinkProperty link;

		public StandardListItemViewHolder(View view)
		{
			super(view);

			image = (ImageView)view.findViewById(R.id.image);
			title = (TextView)view.findViewById(R.id.title);
			description = (TextView)view.findViewById(R.id.description);
		}

		@Override public void populateView(final StandardListItem model)
		{
			link = model.getLink();
			image.setVisibility(View.GONE);

			if (model.getImage() != null)
			{
				UiSettings.getInstance().getImageLoader().displayImage(model.getImage().getSrc(), image, new SimpleImageLoadingListener()
				{
					@Override public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
					{
						if (loadedImage != null)
						{
							image.setVisibility(View.VISIBLE);
						}
					}

					@Override public void onLoadingFailed(String imageUri, View view, FailReason failReason)
					{
						if (!imageUri.equalsIgnoreCase(model.getImage().getFallbackSrc()))
						{
							UiSettings.getInstance().getImageLoader().displayImage(model.getImage().getFallbackSrc(), image, this);
						}
					}
				});
			}

			description.setVisibility(View.GONE);
			title.setVisibility(View.GONE);

			if (model.getTitle() != null)
			{
				String content = UiSettings.getInstance().getTextProcessor().process(model.getTitle().getContent());

				if (!TextUtils.isEmpty(content))
				{
					title.setText(content);
					title.setVisibility(View.VISIBLE);
				}
			}

			if (model.getDescription() != null)
			{
				String content = UiSettings.getInstance().getTextProcessor().process(model.getDescription().getContent());

				if (!TextUtils.isEmpty(content))
				{
					description.setText(content);
					description.setVisibility(View.VISIBLE);
				}
			}
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
