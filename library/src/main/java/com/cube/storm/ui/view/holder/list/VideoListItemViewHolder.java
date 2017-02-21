package com.cube.storm.ui.view.holder.list;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.activity.VideoPlayerActivity;
import com.cube.storm.ui.lib.EventHook;
import com.cube.storm.ui.model.descriptor.VideoPageDescriptor;
import com.cube.storm.ui.model.list.VideoListItem;
import com.cube.storm.ui.model.property.VideoProperty;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.Populator;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

import java.util.ArrayList;

/**
 * View holder for {@link com.cube.storm.ui.model.list.VideoListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @Project LightningUi
 */
public class VideoListItemViewHolder extends ViewHolder<VideoListItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public VideoListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item_view, parent, false);
			return new VideoListItemViewHolder(view);
		}
	}

	protected ImageView image;
	protected ProgressBar progress;
	protected LinearLayout embeddedLinksContainer;
	protected VideoListItem model;

	public VideoListItemViewHolder(View view)
	{
		super(view);

		image = (ImageView)view.findViewById(R.id.image);
		progress = (ProgressBar)view.findViewById(R.id.progress);
		embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
	}

	@Override public void populateView(final VideoListItem model)
	{
		this.model = model;
		image.populate(model.getImage(), progress);
		Populator.populate(embeddedLinksContainer, model.getEmbeddedLinks());

		itemView.setOnClickListener(new OnClickListener()
		{
			@Override public void onClick(View v)
			{
				ArrayList<VideoProperty> arrayList = new ArrayList<VideoProperty>();
				arrayList.addAll(model.getVideos());

				for (EventHook eventHook : UiSettings.getInstance().getEventHooks())
				{
					eventHook.onViewLinkedClicked(itemView, model, arrayList.get(0).getSrc());
				}

				VideoPageDescriptor pageDescriptor = new VideoPageDescriptor();
				pageDescriptor.setType("content");
				pageDescriptor.setSrc(arrayList.get(0).getSrc().getDestination());

				Intent video = UiSettings.getInstance().getIntentFactory().getIntentForPageDescriptor(v.getContext(), pageDescriptor);

				if (video != null)
				{
					video.putExtra(VideoPlayerActivity.EXTRA_FILE_NAME, "Video Asset");
					video.putExtra(VideoPlayerActivity.EXTRA_VIDEOS, arrayList);

					v.getContext().startActivity(video);
				}
			}
		});
	}
}
