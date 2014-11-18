package com.cube.storm.ui.view.holder;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.DescriptionListItem;
import com.cube.storm.ui.model.property.LinkProperty;

/**
 * View holder for {@link com.cube.storm.ui.model.list.DescriptionListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class DescriptionListItemHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.description_list_item_view, parent, false);
		mViewHolder = new DescriptionListItemViewHolder(view);

		return mViewHolder;
	}

	private class DescriptionListItemViewHolder extends ViewHolder<DescriptionListItem>
	{
		protected TextView title;
		protected TextView description;
		protected LinearLayout embeddedLinksContainer;

		public DescriptionListItemViewHolder(View view)
		{
			super(view);

			title = (TextView)view.findViewById(R.id.title);
			description = (TextView)view.findViewById(R.id.description);
			embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
		}

		@Override public void populateView(DescriptionListItem model)
		{
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
			else
			{
				description.setVisibility(View.GONE);
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
	}
}
