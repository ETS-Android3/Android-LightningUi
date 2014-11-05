package com.cube.storm.ui.view.holder;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.TitleListItem;

/**
 * View holder for {@link com.cube.storm.ui.model.list.TitleListItem} in the adapter
 *
 * @author Alan Le Fournis
 * @project StormUI
 */
public class TitleListItemHolder extends Holder<TitleListItem>
{
	protected TextView title;

	@Override public View createView(ViewGroup parent)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_list_item_view, parent, false);
		title = (TextView)view.findViewById(R.id.title);

		return view;
	}

	@Override public void populateView(TitleListItem model)
	{
		title.setVisibility(View.GONE);

		if (model.getTitle() != null)
		{
			String content = UiSettings.getInstance().getTextProcessor().process(model.getTitle().getContent());

			if (!TextUtils.isEmpty(content))
			{
				title.setText(content);
				title.setVisibility(View.VISIBLE);
			}
		}
	}
}
