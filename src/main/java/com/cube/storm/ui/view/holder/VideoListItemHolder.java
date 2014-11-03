package com.cube.storm.ui.view.holder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.activity.VideoPlayerActivity;
import com.cube.storm.ui.model.list.VideoListItem;
import com.cube.storm.ui.model.property.VideoProperty;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class VideoListItemHolder extends Holder<VideoListItem>
{
	protected ImageView image;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item_view, parent, false);
		image = (ImageView)view.findViewById(R.id.image);

		return view;
	}

	@Override public void populateView(final VideoListItem model)
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

			image.setOnClickListener(new OnClickListener()
			{
				@Override public void onClick(View v)
				{
					Intent video = new Intent(image.getContext(), VideoPlayerActivity.class);
					ArrayList<VideoProperty> arrayList = new ArrayList<VideoProperty>();
					arrayList.addAll(model.getVideos());

					Bundle bundle = new Bundle();
					bundle.putString(VideoPlayerActivity.EXTRA_FILE_NAME, "Video Asset");
					bundle.putSerializable(VideoPlayerActivity.EXTRA_VIDEOS, arrayList);
					video.putExtras(bundle);
					image.getContext().startActivity(video);
				}
			});
		}
	}
}
