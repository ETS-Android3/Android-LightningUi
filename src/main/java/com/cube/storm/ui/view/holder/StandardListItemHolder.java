package com.cube.storm.ui.view.holder;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.StandardListItem;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.view.ViewClickable;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * View holder for {@link com.cube.storm.ui.model.list.StandardListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class StandardListItemHolder extends Holder<StandardListItem> implements ViewClickable<StandardListItem>
{
	protected ImageView image;
	protected TextView title;
	protected TextView description;
	protected LinearLayout embeddedLinksContainer;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_list_item_view, parent, false);
		image = (ImageView)view.findViewById(R.id.image);
		title = (TextView)view.findViewById(R.id.title);
		description = (TextView)view.findViewById(R.id.description);
		embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);

		return view;
	}

	@Override public void populateView(final StandardListItem model)
	{
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

		if (model.getEmbeddedLinks() != null)
		{
			embeddedLinksContainer.removeAllViews();

			for (LinkProperty linkProperty : model.getEmbeddedLinks())
			{
				final LinkProperty property = linkProperty;
				View embeddedLinkView = LayoutInflater.from(embeddedLinksContainer.getContext()).inflate(R.layout.button_embedded_link, embeddedLinksContainer, false);

				if (embeddedLinkView != null)
				{
					Button button = (Button)embeddedLinkView.findViewById(R.id.button);
					button.setVisibility(View.GONE);
					String content = UiSettings.getInstance().getTextProcessor().process(linkProperty.getTitle().getContent());

					if (!TextUtils.isEmpty(content))
					{
						button.setText(content);
						button.setVisibility(View.VISIBLE);
					}

					button.setOnClickListener(new OnClickListener()
					{
						@Override public void onClick(View v)
						{
							UiSettings.getInstance().getLinkHandler().handleLink(v.getContext(), property);
						}
					});

					embeddedLinksContainer.setVisibility(View.VISIBLE);
					embeddedLinksContainer.addView(button);
				}
			}
		}
	}

	@Override public void onClick(@NonNull StandardListItem model, @NonNull View view)
	{
		if (model.getLink() != null)
		{
			UiSettings.getInstance().getLinkHandler().handleLink(view.getContext(), model.getLink());
		}
	}
}
