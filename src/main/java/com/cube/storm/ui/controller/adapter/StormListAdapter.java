package com.cube.storm.ui.controller.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.lib.spec.DividerSpec;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.grid.GridItem;
import com.cube.storm.ui.model.list.List;
import com.cube.storm.ui.model.list.List.ListFooter;
import com.cube.storm.ui.model.list.List.ListHeader;
import com.cube.storm.ui.model.list.ListItem;
import com.cube.storm.ui.view.holder.GridViewHolder;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The base adapter used for displaying Storm views in a list. Using an adapter to do such a task has
 * the benefit of view recycling which makes the content smooth to scroll.
 * <p />
 * This adapter only supports {@link com.cube.storm.ui.model.Model} classes which have a defined {@link ViewHolder} counter-class.
 * <p />
 * <b>Usage</b>
 * <p/>
 * <b>Problems</b>
 * Problems can arise with this method of rendering content, specifically with render efficiency where
 * the views are not being recycled because there is only 1 of its view type in the list. This is the
 * equivalent of having all of the views inflated into a {@link android.widget.ScrollView}. The smoothness
 * of the scrolling (depending on how much content there is) diminishes with the amount of unique content
 * that the list is rendering.
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class StormListAdapter extends RecyclerView.Adapter<ViewHolder<?>>
{
	/**
	 * The list of models of the views we are rendering in the list. This is a 1 dimensional representation
	 * of a multi-dimensional 'sub listed' array set which is outlined by the json. When setting the items
	 * in this list, the models have to be traversed in order to build the 1 dimensional list for the
	 * adapter to work correctly.
	 */
	private ArrayList<Model> items = new ArrayList<>();

	/**
	 * The different unique item types. This is used to tell the adapter how many unique views we're
	 * going to be rendering so it knows what and when to recycle. The list is just for index based
	 * convenience, the object type in the list is a reference to the view holder class we will use
	 * to render said view.
	 */
	private ArrayList<Class<? extends ViewHolderFactory>> itemTypes = new ArrayList<>();

	/**
	 * Divider spec to use when laying out the children
	 */
	private DividerSpec dividerSpec;

	public StormListAdapter()
	{
		dividerSpec = UiSettings.getInstance().getDividerSpec();
	}

	public StormListAdapter(Collection<? extends Model> items)
	{
		dividerSpec = UiSettings.getInstance().getDividerSpec();
		setItems(items);
	}

	public void setDividerSpec(DividerSpec spec)
	{
		this.dividerSpec = dividerSpec;
	}

	/**
	 * Sets the items in the collection. Filters out any model that does not have a defined {@link ViewHolder}
	 *
	 * @param items The new items to set. Can be null to clear the list.
	 */
	public void setItems(@Nullable Collection<? extends Model> items)
	{
		if (items != null)
		{
			this.items = new ArrayList<>(items.size());
			this.itemTypes = new ArrayList<>(items.size() / 2);

			for (Model item : items)
			{
				addItem(item);
			}

			// Add dividers
			if (dividerSpec != null)
			{
				ArrayList<Model> tmp = new ArrayList<>(this.items);

				int count = tmp.size();
				int offset = 0;
				for (int index = 0; index < count; index++)
				{
					ListItem divider = dividerSpec.shouldAddDivider(index, tmp);

					if (divider != null)
					{
						addItem(index + (offset++), divider);
					}
				}
			}
		}
		else
		{
			this.items = new ArrayList<>(0);
			this.itemTypes = new ArrayList<>(0);
		}
	}

	/**
	 * Adds an item to the list, only if a holder class is found as returned by {@link com.cube.storm.ui.lib.factory.ViewFactory#getHolderForView(String)}
	 *
	 * @param item The model to add to the list
	 */
	public void addItem(@NonNull Model item)
	{
		addItem(this.items.size(), item);
	}

	/**
	 * Adds an item to the list, only if a holder class is found as returned by {@link com.cube.storm.ui.lib.factory.ViewFactory#getHolderForView(String)}
	 *
	 * @param index The index to where to add the item
	 * @param item The model to add to the list
	 */
	public void addItem(int index, @NonNull Model item)
	{
		if (item instanceof List)
		{
			if (((List)item).getHeader() != null && !TextUtils.isEmpty(UiSettings.getInstance().getTextProcessor().process(((List)item).getHeader().getContent())))
			{
				ListHeader header = new ListHeader();
				header.setHeader(((List)item).getHeader());

				addItem(header);
			}

			if (((List)item).getChildren() != null)
			{
				for (Model subItem : ((List)item).getChildren())
				{
					if (subItem != null)
					{
						addItem(subItem);
					}
				}
			}

			if (((List)item).getFooter() != null && !TextUtils.isEmpty(UiSettings.getInstance().getTextProcessor().process(((List)item).getFooter().getContent())))
			{
				ListFooter footer = new ListFooter();
				footer.setFooter(((List)item).getFooter());

				addItem(footer);
			}
		}
		else
		{
			Class<? extends ViewHolderFactory> holderClass = UiSettings.getInstance().getViewFactory().getHolderForView(item.getClassName());

			if (holderClass != null)
			{
				this.items.add(index, item);
			}

			if (!this.itemTypes.contains(holderClass))
			{
				this.itemTypes.add(holderClass);
			}
		}
	}

	public Model getItem(int position)
	{
		return items.get(position);
	}

	@Override public long getItemId(int position)
	{
		return getItem(position).hashCode();
	}

	@Override public int getItemCount()
	{
		return items.size();
	}

	@Override public ViewHolder<?> onCreateViewHolder(ViewGroup viewGroup, int viewType)
	{
		ViewHolder<?> holder;

		try
		{
			ViewHolderFactory holderFactory = itemTypes.get(viewType).getConstructor().newInstance();
			holder = holderFactory.createViewHolder(viewGroup);
		}
		catch (Exception e)
		{
			throw new IllegalStateException("Could not instantiate a new holder" + e.getMessage(), e);
		}

		return holder;
	}

	@Override public void onBindViewHolder(ViewHolder viewHolder, int position)
	{
		try
		{
			if(viewHolder instanceof GridViewHolder)
			{
				GridItem model = (GridItem)getItem(position);
				((GridViewHolder)viewHolder).checkSpan(model);
				viewHolder.populateView(model);
			}
			else
			{
				viewHolder.populateView(getItem(position));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override public int getItemViewType(int position)
	{
		Model view = items.get(position);
		return itemTypes.indexOf(UiSettings.getInstance().getViewFactory().getHolderForView(view.getClassName()));
	}
}
