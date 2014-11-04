package com.cube.storm.ui.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.ButtonListItem;

/**
 * View holder for {@link com.cube.storm.ui.model.list.ButtonListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class ButtonListItemHolder extends ViewHolderController
{

	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_list_item_view, parent, false);
		return new ButtonListItemViewHolder(view);
	}

	private class ButtonListItemViewHolder extends ViewHolder<ButtonListItem>
	{
		protected TextView title;
		protected Button button;

		public ButtonListItemViewHolder(View view)
		{
			super(view);

			title = (TextView)view.findViewById(R.id.title);
			button = (Button)view.findViewById(R.id.button);
		}

		@Override public void populateView(ButtonListItem model)
		{
			if (model.getTitle() != null)
			{
				title.setText(UiSettings.getInstance().getTextProcessor().process(model.getTitle().getContent()));
				title.setVisibility(View.VISIBLE);
			}
			else
			{
				title.setVisibility(View.GONE);
			}

			if (model.getButton() != null)
			{
				button.setText(UiSettings.getInstance().getTextProcessor().process(model.getButton().getTitle().getContent()));
			}
		}
	}
}
