package com.cube.storm.ui.view.holder.list;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.lib.helper.ImageHelper;
import com.cube.storm.ui.model.list.HeaderListItem;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderController;

/**
 * View holder for {@link com.cube.storm.ui.model.list.HeaderListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class HeaderListItemHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_list_item_view, parent, false);
		mViewHolder = new HeaderListItemViewHolder(view);

		return mViewHolder;
	}

	public class HeaderListItemViewHolder extends ViewHolder<HeaderListItem>
	{
		protected ImageView image;
		protected TextView title;
		protected TextView description;

		public HeaderListItemViewHolder(View view)
		{
			super(view);

			image = (ImageView)view.findViewById(R.id.image_view);
			title = (TextView)view.findViewById(R.id.title);
			description = (TextView)view.findViewById(R.id.description);
		}

		@Override public void populateView(final HeaderListItem model)
		{
			image.setImageBitmap(null);
			title.setVisibility(View.GONE);
			description.setVisibility(View.GONE);

			ImageHelper.displayImage(image, model.getImage());

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
	}
}
