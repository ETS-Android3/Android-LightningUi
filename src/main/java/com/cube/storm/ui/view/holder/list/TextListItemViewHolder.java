package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.TextListItem;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.list.TextListItem} in the adapter
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class TextListItemViewHolder extends ViewHolder<TextListItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public TextListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_list_item_view, parent, false);
			return new TextListItemViewHolder(view);
		}
	}

	protected TextView textView;

	public TextListItemViewHolder(View view)
	{
		super(view);
		textView = (TextView)view.findViewById(R.id.text);
	}

	@Override public void populateView(TextListItem model)
	{
		textView.populate(model.getDescription());
	}
}
