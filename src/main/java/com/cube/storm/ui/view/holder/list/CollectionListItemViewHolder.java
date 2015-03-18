package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.collection.CollectionItem;
import com.cube.storm.ui.model.list.collection.CollectionListItem;
import com.cube.storm.ui.view.Populator;
import com.cube.storm.ui.view.ViewClickable;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class CollectionListItemViewHolder extends ViewHolder<CollectionListItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public CollectionListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_list_item_view, parent, false);
			return new CollectionListItemViewHolder(view);
		}
	}

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

		Populator.populate(embeddedLinksContainer, model.getEmbeddedLinks());
	}

	protected View getView(final CollectionItem itemModel, View view)
	{
		if (itemModel != null)
		{
			ViewHolderFactory holderFactory = null;
			ViewHolder<? super CollectionItem> holder = null;

			if (view == null)
			{
				try
				{
					Class<? extends ViewHolderFactory> cls = UiSettings.getInstance().getViewFactory().getHolderForView(itemModel.getClassName());
					holderFactory = cls.newInstance();
					holder = (ViewHolder<? super CollectionItem>)holderFactory.createViewHolder((ViewGroup)linearLayout.getParent());
					holder.populateView(itemModel);
					holder.itemView.setTag(holder);
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
					holder = (ViewHolder<? super CollectionItem>)view.getTag();
				}
			}

			if (view != null)
			{
				final ViewHolder<? super CollectionItem> tmp = holder;

				if (tmp instanceof ViewClickable)
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
