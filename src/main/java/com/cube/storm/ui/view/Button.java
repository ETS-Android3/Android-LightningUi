package com.cube.storm.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.model.property.ButtonProperty;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project Storm
 */
public class Button extends android.widget.Button
{
	public Button(Context context)
	{
		super(context);
	}

	public Button(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public Button(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	public Button(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public void populate(final ButtonProperty button)
	{
		setVisibility(View.GONE);

		if (button != null && button.getTitle() != null)
		{
			if (button.getLink() != null)
			{
				this.setOnClickListener(new OnClickListener()
				{
					@Override public void onClick(View v)
					{
						UiSettings.getInstance().getLinkHandler().handleLink(getContext(), button.getLink());
					}
				});
			}

			String buttonContent = UiSettings.getInstance().getTextProcessor().process(button.getTitle().getContent());

			if (!TextUtils.isEmpty(buttonContent))
			{
				setText(buttonContent);
				setVisibility(View.VISIBLE);
			}
		}

	}
}
