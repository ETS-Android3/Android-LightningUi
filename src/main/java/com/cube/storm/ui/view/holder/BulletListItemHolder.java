package com.cube.storm.ui.view.holder;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.BulletListItem;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

/**
 * View holder for {@link com.cube.storm.ui.model.list.BulletListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class BulletListItemHolder extends Holder<BulletListItem>
{
	protected ImageView bullet;
	protected TextView title;
	protected TextView description;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bullet_list_item_view, parent, false);
		bullet = (ImageView)view.findViewById(R.id.bullet);
		title = (TextView)view.findViewById(R.id.title);
		description = (TextView)view.findViewById(R.id.description);

		return view;
	}

	@Override public void populateView(final BulletListItem model)
	{

		if (model.getBullet() != null)
		{
			UiSettings.getInstance().getImageLoader().displayImage(model.getBullet().getSrc(), bullet, new SimpleImageLoadingListener()
			{
				@Override public void onLoadingFailed(String imageUri, View view, FailReason failReason)
				{
					UiSettings.getInstance().getImageLoader().displayImage(model.getBullet().getFallbackSrc(), bullet);
				}
			});
		}

		if (model.getTitle() != null)
		{
			title.setText(model.getTitle().getContent());
		}

		if (model.getDescription() != null && !TextUtils.isEmpty(model.getDescription().getContent()))
		{
			description.setText(model.getDescription().getContent());
			description.setVisibility(View.VISIBLE);
		}
		else
		{
			description.setVisibility(View.GONE);
		}
	}
}
