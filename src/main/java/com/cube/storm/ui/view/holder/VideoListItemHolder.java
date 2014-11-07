package com.cube.storm.ui.view.holder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.activity.VideoPlayerActivity;
import com.cube.storm.ui.model.descriptor.VideoPageDescriptor;
import com.cube.storm.ui.model.list.VideoListItem;
import com.cube.storm.ui.model.property.VideoProperty;
import com.cube.storm.ui.view.ViewClickable;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

/**
 * View holder for {@link com.cube.storm.ui.model.list.VideoListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class VideoListItemHolder extends Holder<VideoListItem> implements ViewClickable<VideoListItem>
{
	protected ImageView image;
	protected ProgressBar progress;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item_view, parent, false);
		image = (ImageView)view.findViewById(R.id.image);
		progress = (ProgressBar)view.findViewById(R.id.progress);

		return view;
	}

	@Override public void populateView(final VideoListItem model)
	{
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
	}

	@Override public void onClick(@NonNull VideoListItem model, @NonNull View view)
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
