package com.cube.storm.ui.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import java.lang.reflect.Field;

public class AspectRatioImageView extends ImageView
{
	public AspectRatioImageView(Context context)
	{
		super(context);
	}

	public AspectRatioImageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public AspectRatioImageView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (getDrawable() != null)
		{
			int width = MeasureSpec.getSize(widthMeasureSpec);
			int height = Math.max(1, (width * getDrawable().getIntrinsicHeight())) / Math.max(1, getDrawable().getIntrinsicWidth());

			if (Build.VERSION.SDK_INT >= 16)
			{
				int maxHeight = getMaxHeight();

				if (maxHeight > -1)
				{
					height = Math.min(height, maxHeight);
				}
			}
			else
			{
				try
				{
					Field maxHeightField = ImageView.class.getDeclaredField("mMaxHeight");
					maxHeightField.setAccessible(true);

					int maxHeight = (Integer)maxHeightField.get(this);

					if (maxHeight > -1)
					{
						height = Math.min(height, maxHeight);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			setMeasuredDimension(width, height);
		}
	}
}
