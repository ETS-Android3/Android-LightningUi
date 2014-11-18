package com.cube.storm.ui.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.TextListItem;

/**
 * View holder for {@link com.cube.storm.ui.model.list.TextListItem} in the adapter
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class TextListItemHolder extends ViewHolderController
{
	@Override public ViewHolder createViewHolder(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_list_item_view, parent, false);
		mViewHolder = new TextListItemViewHolder(view);

		return mViewHolder;
	}

	public class TextListItemViewHolder extends ViewHolder<TextListItem>
	{
		protected TextView text;

		public TextListItemViewHolder(View view)
		{
			super(view);
			text = (TextView)view.findViewById(R.id.text);
		}

		@Override public void populateView(TextListItem model)
		{
			if (model.getDescription() != null)
			{
				text.setText(UiSettings.getInstance().getTextProcessor().process(model.getDescription().getContent()));
			}
		}
	}
}
