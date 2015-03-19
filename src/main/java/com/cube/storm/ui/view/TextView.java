package com.cube.storm.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.model.property.TextProperty;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project Storm
 */
public class TextView extends android.widget.TextView implements View.OnClickListener
{
	private LinkProperty linkProperty = null;

	public TextView(Context context)
	{
		super(context);
	}

	public TextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public TextView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	public TextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public void populate(TextProperty text)
	{
		populate(text, null);
	}

	public void populate(TextProperty text, LinkProperty link)
	{
		this.setVisibility(View.GONE);

		if (text != null)
		{
			String content = UiSettings.getInstance().getTextProcessor().process(text.getContent());
			if (!TextUtils.isEmpty(content))
			{
				linkProperty = link;
				this.setText(content);
				this.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override public void onClick(View view)
	{
		if (linkProperty != null)
		{
			UiSettings.getInstance().getLinkHandler().handleLink(view.getContext(), linkProperty);
		}
	}
}
