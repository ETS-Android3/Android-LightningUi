package com.cube.storm.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.model.property.ButtonProperty;
import com.cube.storm.ui.model.property.LinkProperty;

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

		if (button != null)
		{
			populate(button.getLink());

			if (button.getTitle() != null)
			{
				String buttonContent = UiSettings.getInstance().getTextProcessor().process(button.getTitle().getContent());

				if (!TextUtils.isEmpty(buttonContent))
				{
					setVisibility(View.VISIBLE);
					setText(buttonContent);
				}
			}
		}
	}

	public void populate(final LinkProperty link)
	{
		setVisibility(View.GONE);

		if (link != null)
		{
			// TODO: Don't overwrite user's custom clickListener
			this.setOnClickListener(new OnClickListener()
			{
				@Override public void onClick(View v)
				{
					UiSettings.getInstance().getLinkHandler().handleLink(getContext(), link);
				}
			});

			if (link.getTitle() != null)
			{
				String buttonContent = UiSettings.getInstance().getTextProcessor().process(link.getTitle().getContent());

				if (!TextUtils.isEmpty(buttonContent))
				{
					setVisibility(View.VISIBLE);
					setText(buttonContent);
				}
			}
		}
		else
		{
			this.setOnClickListener(null);
		}
	}
}
