package com.cube.storm.ui.view.holder.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.ButtonListItem;
import com.cube.storm.ui.view.Button;
import com.cube.storm.ui.view.Populator;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.list.ButtonListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class ButtonListItemViewHolder extends ViewHolder<ButtonListItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public ButtonListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_list_item_view, parent, false);
			return new ButtonListItemViewHolder(view);
		}
	}

	protected TextView title;
	protected Button button;
	protected LinearLayout embeddedLinksContainer;

	public ButtonListItemViewHolder(View view)
	{
		super(view);

		title = (TextView)view.findViewById(R.id.title);
		button = (Button)view.findViewById(R.id.button);
		embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
	}

	@Override public void populateView(ButtonListItem model)
	{
		title.populate(model.getTitle());
		button.populate(model.getButton());
		Populator.populate(embeddedLinksContainer, model.getEmbeddedLinks());
	}
}
