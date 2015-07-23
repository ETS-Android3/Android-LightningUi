package com.cube.storm.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.model.property.LinkProperty;
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
		setTypeface(context);
	}

	public TextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		setTypeface(context);
	}

	public TextView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		setTypeface(context);
	}

	public TextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
		setTypeface(context);
	}

	private void setTypeface(Context context)
	{
		super.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/courier.ttf"));
	}

	public void populate(TextProperty text)
	{
		populate(text, null);
	}

	public void populate(@Nullable TextProperty text, @Nullable final LinkProperty link)
	{
		this.setVisibility(View.GONE);

		if (text != null)
		{
			String content = UiSettings.getInstance().getTextProcessor().process(text.getContent());
			if (!TextUtils.isEmpty(content))
			{
				// TODO: Don't overwrite user's custom clickListener
				if (link != null)
				{
					this.setOnClickListener(new OnClickListener()
					{
						@Override public void onClick(View v)
						{
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
