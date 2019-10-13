package com.cube.storm.ui.view;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.R;
import com.cube.storm.ui.model.list.TextListItem;

/**
 * Custom view class for the {@link com.cube.storm.ui.model.list.TextListItem} model
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class TextListItemView extends LinearLayout
{
	private TextView title;

	public TextListItemView(Context context)
	{
		super(context);
		setupView();
	}

	public TextListItemView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		setupView();
	}

	public TextListItemView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		setupView();
	}

	private void setupView()
	{
		LayoutInflater.from(getContext()).inflate(R.layout.text_list_item_view_stub, this, true);
		title = (TextView)findViewById(R.id.title);
	}

	public void populate(@NonNull TextListItem model)
	{
		if (model.getDescription() != null)
		{
			title.setText(UiSettings.getInstance().getTextProcessor().process(model.getDescription()));
		}
	}
}
