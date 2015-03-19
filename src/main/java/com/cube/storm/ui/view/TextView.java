package com.cube.storm.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.model.property.TextProperty;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @Project LightningUi
 */
public class TextView extends android.widget.TextView
{
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
		this.setVisibility(View.GONE);

		if (text != null)
		{
			String content = UiSettings.getInstance().getTextProcessor().process(text.getContent());
			if (!TextUtils.isEmpty(content))
			{
				this.setText(content);
				this.setVisibility(View.VISIBLE);
			}
		}
	}
}
