package com.cube.storm.ui.view.holder;

import android.text.TextUtils;
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
public class ButtonListItemHolder extends Holder<ButtonListItem>
{
	protected TextView title;
	protected Button button;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_list_item_view, parent, false);
		title = (TextView)view.findViewById(R.id.title);
		button = (Button)view.findViewById(R.id.button);

		return view;
	}

	@Override public void populateView(ButtonListItem model)
	{
		title.setVisibility(View.GONE);
		button.setVisibility(View.GONE);

		if (model.getTitle() != null)
		{
			String content = UiSettings.getInstance().getTextProcessor().process(model.getTitle().getContent());

			if (!TextUtils.isEmpty(content))
			{
				title.setText(content);
				title.setVisibility(View.VISIBLE);
			}
		}

		if (model.getButton() != null)
		{
			String content = UiSettings.getInstance().getTextProcessor().process(model.getButton().getTitle().getContent());

			if (!TextUtils.isEmpty(content))
			{
				button.setText(content);
				button.setVisibility(View.VISIBLE);
			}
		}
	}
}
