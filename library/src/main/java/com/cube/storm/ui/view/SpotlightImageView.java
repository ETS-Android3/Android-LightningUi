package com.cube.storm.ui.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * This class is a simple wrapper around ImageView for spotlight images.
 * It makes sure the aspect ratio is always 5:2 (landscape - width is greater than height)
 */
public class SpotlightImageView extends ImageView
{
	public SpotlightImageView(Context context)
	{
		super(context);
	}

	public SpotlightImageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public SpotlightImageView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	@Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int width = MeasureSpec.getSize(widthMeasureSpec);
		setMeasuredDimension(width, (int) (width * 0.4));
	}
}
