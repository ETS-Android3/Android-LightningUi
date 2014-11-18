package com.cube.storm.ui.view.holder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.activity.VideoPlayerActivity;
import com.cube.storm.ui.model.descriptor.VideoPageDescriptor;
import com.cube.storm.ui.model.list.VideoListItem;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.model.property.VideoProperty;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

/**
 * View holder for {@link com.cube.storm.ui.model.list.VideoListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class VideoListItemHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item_view, parent, false);
		mViewHolder = new VideoListItemViewHolder(view);

		return mViewHolder;
	}

	public class VideoListItemViewHolder extends ViewHolder<VideoListItem> implements OnClickListener
	{
		protected ImageView image;
		protected ProgressBar progress;
		protected LinearLayout embeddedLinksContainer;
		protected VideoListItem model;

		public VideoListItemViewHolder(View view)
		{
			super(view);

			view.setOnClickListener(this);
			
			image = (ImageView)view.findViewById(R.id.image);
			progress = (ProgressBar)view.findViewById(R.id.progress);
			embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
		}

		@Override public void populateView(final VideoListItem model)
		{
			this.model = model;
			if (model.getImage() != null)
			{
				UiSettings.getInstance().getImageLoader().displayImage(model.getImage().getSrc(), image, new SimpleImageLoadingListener()
				{
					@Override public void onLoadingStarted(String imageUri, View view)
					{
						image.setVisibility(View.INVISIBLE);
						progress.setVisibility(View.VISIBLE);
					}

					@Override public void onLoadingFailed(String imageUri, View view, FailReason failReason)
					{
						if (!imageUri.equalsIgnoreCase(model.getImage().getFallbackSrc()))
						{
							UiSettings.getInstance().getImageLoader().displayImage(model.getImage().getFallbackSrc(), image, this);
						}

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
						button.setText(property.getTitle().getContent());

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

		@Override public void onClick(View view)
		{
			ArrayList<VideoProperty> arrayList = new ArrayList<VideoProperty>();
			arrayList.addAll(model.getVideos());

			VideoPageDescriptor pageDescriptor = new VideoPageDescriptor();
			pageDescriptor.setType("content");
			pageDescriptor.setSrc(arrayList.get(0).getSrc().getDestination());

			Intent video = UiSettings.getInstance().getIntentFactory().getIntentForPageDescriptor(view.getContext(), pageDescriptor);

			if (video != null)
			{
				video.putExtra(VideoPlayerActivity.EXTRA_FILE_NAME, "Video Asset");
				video.putExtra(VideoPlayerActivity.EXTRA_VIDEOS, arrayList);

				view.getContext().startActivity(video);
			}
		}
	}
}
