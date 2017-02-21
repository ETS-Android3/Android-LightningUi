package com.cube.storm.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.lib.EventHook;
import com.cube.storm.ui.model.property.ButtonProperty;
import com.cube.storm.ui.model.property.LinkProperty;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @Project LightningUi
 */
public class Button extends android.widget.Button
{
	private ButtonProperty buttonProperty;

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

		this.buttonProperty = button;

		if (button != null)
		{
			populate(button.getLink());

			if (button.getTitle() != null)
			{
				String buttonContent = UiSettings.getInstance().getTextProcessor().process(button.getTitle());

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
					for (EventHook eventHook : UiSettings.getInstance().getEventHooks())
					{
						eventHook.onViewLinkedClicked(v, buttonProperty, link);
					}

					UiSettings.getInstance().getLinkHandler().handleLink(v.getContext(), link);
				}
			});

			if (link.getTitle() != null)
			{
				String buttonContent = UiSettings.getInstance().getTextProcessor().process(link.getTitle());

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
