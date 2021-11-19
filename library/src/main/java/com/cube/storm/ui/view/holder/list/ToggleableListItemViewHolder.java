package com.cube.storm.ui.view.holder.list;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.lib.EventHook;
import com.cube.storm.ui.model.list.ToggleableListItem;
import com.cube.storm.ui.view.ImageView;
import com.cube.storm.ui.view.Populator;
import com.cube.storm.ui.view.TextView;
import com.cube.storm.ui.view.holder.ViewHolder;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View holder for {@link com.cube.storm.ui.model.list.ToggleableListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @Project LightningUi
 */
public class ToggleableListItemViewHolder extends ViewHolder<ToggleableListItem>
{
	public static class Factory extends ViewHolderFactory
	{
		@Override public ToggleableListItemViewHolder createViewHolder(ViewGroup parent)
		{
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toggleable_list_item_view, parent, false);
			return new ToggleableListItemViewHolder(view);
		}
	}

	protected ViewGroup toggleContainer;
	protected ImageView expandIcon;
	protected TextView title;
	protected TextView description;
	protected LinearLayout embeddedLinksContainer;
	private String titleText;

	public ToggleableListItemViewHolder(View view)
	{
		super(view);

		toggleContainer = (ViewGroup)view.findViewById(R.id.toggle_container);
		expandIcon = (ImageView)view.findViewById(R.id.expand_icon);
		title = (TextView)view.findViewById(R.id.title);
		description = (TextView)view.findViewById(R.id.description);
		embeddedLinksContainer = (LinearLayout)view.findViewById(R.id.embedded_links_container);
	}

	@Override public void populateView(final ToggleableListItem model)
	{
		setIsRecyclable(false);
		//ARCFA-229 Toggleable items announce expanded state.
		if (model.getTitle() != null)
		{
			titleText = UiSettings.getInstance().getTextProcessor().process(model.getTitle());
			if (!TextUtils.isEmpty(titleText))
			{
				title.setVisibility(View.VISIBLE);
				title.setText(titleText);
				title.setContentDescription(titleText + ". Description collapsed");
			}
			else
			{
				title.setVisibility(View.GONE);
			}
		}

		description.populate(model.getDescription());
		Populator.populate(embeddedLinksContainer, model.getEmbeddedLinks());

		// Use model field instead of View#getTag() which is always null
		expandContainer(model.getExpanded(), model);

		itemView.setOnClickListener(new OnClickListener()
		{
			@Override public void onClick(View v)
			{
				for (EventHook eventHook : UiSettings.getInstance().getEventHooks())
				{
					eventHook.onViewClicked(itemView, model);
				}

				// Invert expansion state when clicked
				expandContainer(!model.getExpanded(), model);
			}
		});
	}

	/**
	 * Expands a {@link ToggleableListItem} and retains it's expanded state for
	 * redrawing the view when scrolling
	 *
	 * @param expand true to expand, false to collapse
	 * @param model the model with which to persist the expanded state
	 */
	private void expandContainer(boolean expand, ToggleableListItem model)
	{
		toggleContainer.setTag(expand);
		model.setExpanded(expand);
		if (expand)
		{
			toggleContainer.setVisibility(View.VISIBLE);
			expandIcon.setImageResource(R.drawable.ic_collapse);
			embeddedLinksContainer.setVisibility(View.VISIBLE);
			title.setContentDescription(titleText + ". Description expanded");
		}
		else
		{
			toggleContainer.setVisibility(View.GONE);
			expandIcon.setImageResource(R.drawable.ic_expand);
			embeddedLinksContainer.setVisibility(View.GONE);
			title.setContentDescription(titleText + ". Description collapsed");
		}
	}
}
