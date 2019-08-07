package com.cube.storm.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.lib.EventHook;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.model.property.TextProperty;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @Project LightningUi
 */
public class TextView extends android.support.v7.widget.AppCompatTextView
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

	public void populate(TextProperty text)
	{
		populate(text, null);
	}

	public void populate(@Nullable final TextProperty text, @Nullable final LinkProperty link)
	{
		this.setVisibility(View.GONE);

		if (text != null)
		{
			String content = UiSettings.getInstance().getTextProcessor().process(text);
			if (!TextUtils.isEmpty(content))
			{
				// TODO: Don't overwrite user's custom clickListener
				if (link != null)
				{
					this.setOnClickListener(new OnClickListener()
					{
						@Override public void onClick(View v)
						{
							for (EventHook eventHook : UiSettings.getInstance().getEventHooks())
							{
								eventHook.onViewLinkedClicked(TextView.this, text, link);
							}

							UiSettings.getInstance().getLinkHandler().handleLink(getContext(), link);
						}
					});
				}

				this.setText(content);
				this.setVisibility(View.VISIBLE);
			}
		}
	}
}
