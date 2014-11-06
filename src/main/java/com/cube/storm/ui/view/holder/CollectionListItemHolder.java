package com.cube.storm.ui.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.collection.CollectionItem;
import com.cube.storm.ui.model.list.collection.CollectionListItem;
import com.cube.storm.ui.view.ViewClickable;

import java.util.ArrayList;
import java.util.List;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class CollectionListItemHolder extends Holder<CollectionListItem>
{
	private ViewHolder holder;
	private View view;
	private LinearLayout linearLayout;

	@Override public View createView(ViewGroup parent)
	{
		view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_list_item_view, null, false);
		linearLayout = (LinearLayout)view.findViewById(R.id.view_container);
		holder = new ViewHolder(view);

		return view;
	}

	@Override public void populateView(CollectionListItem model)
	{
		List<View> views = new ArrayList<View>(model.getCells().size());
		ArrayList<CollectionItem> arrayList = new ArrayList<CollectionItem>();
		arrayList.addAll(model.getCells());

		for (int index = 0; index < arrayList.size(); index++)
		{
			View subView = getView(arrayList.get(index), holder.viewContainer.getChildAt(index));

			if (subView != null)
			{
				views.add(subView);
			}
		}

		holder.viewContainer.removeAllViews();

		for (View subView : views)
		{
			holder.viewContainer.addView(subView);
		}

		view.refreshDrawableState();
	}

	protected View getView(final CollectionItem model, View view)
	{
		if (model != null)
		{
			Holder holder = null;

			if (view == null)
			{
				try
				{
					Class<? extends Holder> cls = UiSettings.getInstance().getViewFactory().getHolderForView(model.getClassName());
					holder = cls.newInstance();
					view = holder.createView((ViewGroup)linearLayout.getParent());
					holder.populateView(model);

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
					holder = (Holder)view.getTag();
					holder.populateView(model);
				}
			}

			if (view != null)
			{
				final Holder tmp = holder;

				if (tmp != null && ViewClickable.class.isAssignableFrom(tmp.getClass()))
				{
					view.setOnClickListener(new OnClickListener()
					{
						@Override public void onClick(View v)
						{
							((ViewClickable)tmp).onClick(model, v);
						}
					});
				}
			}

			return view;
		}
		return null;
	}

	public static class ViewHolder
	{
		public LinearLayout viewContainer;

		public ViewHolder(View root)
		{
			viewContainer = (LinearLayout)root.findViewById(R.id.view_container);
		}
	}
}
