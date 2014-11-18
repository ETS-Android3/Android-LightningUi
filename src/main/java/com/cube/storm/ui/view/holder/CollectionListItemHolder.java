package com.cube.storm.ui.view.holder;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.collection.CollectionItem;
import com.cube.storm.ui.model.list.collection.CollectionListItem;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.view.ViewClickable;

import java.util.ArrayList;
import java.util.List;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class CollectionListItemHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_list_item_view, parent, false);
		mViewHolder = new CollectionListItemViewHolder(view);

		return mViewHolder;
	}

	public class CollectionListItemViewHolder extends ViewHolder<CollectionListItem>
	{
		private LinearLayout linearLayout;
		protected LinearLayout embeddedLinksContainer;

		public CollectionListItemViewHolder(View view)
		{
			super(view);

			linearLayout = (LinearLayout)view.findViewById(R.id.view_container);
			embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
		}

		@Override public void populateView(CollectionListItem model)
		{
			List<View> views = new ArrayList<View>(model.getCells().size());
			ArrayList<CollectionItem> arrayList = new ArrayList<CollectionItem>();
			arrayList.addAll(model.getCells());

			for (int index = 0; index < arrayList.size(); index++)
			{
				View subView = getView(arrayList.get(index), linearLayout.getChildAt(index));

				if (subView != null)
				{
					views.add(subView);
				}
			}

			linearLayout.removeAllViews();

			for (View subView : views)
			{
				linearLayout.addView(subView);
			}

			linearLayout.refreshDrawableState();

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

		protected View getView(final CollectionItem itemModel, View view)
		{
			if (itemModel != null)
			{
				ViewHolderController holder = null;

				if (view == null)
				{
					try
					{
						Class<? extends ViewHolderController> cls = UiSettings.getInstance().getViewFactory().getHolderForView(itemModel.getClassName());
						holder = cls.newInstance();
						view = holder.createViewHolder((ViewGroup)linearLayout.getParent()).itemView;
						holder.getViewHolder().populateView(itemModel);

						view.setTag(holder);
					}
					catch (InstantiationException e)
					{
						e.printStackTrace();
					}
					catch (IllegalAccessException e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					if (view.getTag() != null)
					{
						holder = (ViewHolderController)view.getTag();
						holder.getViewHolder().populateView(itemModel);
					}
				}

				if (view != null)
				{
					final ViewHolderController tmp = holder;

					if (tmp != null && ViewClickable.class.isAssignableFrom(tmp.getClass()))
					{
						view.setOnClickListener(new OnClickListener()
						{
							@Override public void onClick(View v)
							{
								((ViewClickable)tmp).onClick(itemModel, v);
							}
						});
					}
				}
				return view;
			}
			return null;
		}
	}
}
