package com.cube.storm.ui.data;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import lombok.Getter;

public enum ContentSize
{
	SMALL(0.75, "x0.75"),
	MEDIUM(1, "x1.0"),
	LARGE(1.5, "x1.5");

	@Getter double maxPixels;
	@Getter String density;

	private ContentSize(double maxPixels, String densityStr)
	{
		this.maxPixels = maxPixels;
		this.density = densityStr;
	}

	public static ContentSize getDensityForSize(Context context)
	{
		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager window = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display display = window.getDefaultDisplay();
		display.getMetrics(displayMetrics);

		int densityName = displayMetrics.densityDpi;

		if (densityName >= DisplayMetrics.DENSITY_LOW && densityName < DisplayMetrics.DENSITY_MEDIUM)
		{
			return ContentSize.SMALL;
		}
		else if (densityName >= DisplayMetrics.DENSITY_MEDIUM && densityName < DisplayMetrics.DENSITY_TV)
		{
			return ContentSize.MEDIUM;
		}
		else if (densityName >= DisplayMetrics.DENSITY_TV && densityName < DisplayMetrics.DENSITY_XHIGH)
		{
			return ContentSize.LARGE;
		}
		else if (densityName >= DisplayMetrics.DENSITY_XHIGH)
		{
			return ContentSize.LARGE;
		}

		return MEDIUM;
	}
}
