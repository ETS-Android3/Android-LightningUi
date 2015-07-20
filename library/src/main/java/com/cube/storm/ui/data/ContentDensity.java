package com.cube.storm.ui.data;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import lombok.Getter;

public enum ContentDensity
{
	x0_75(0.75, "x0.75"),
	x1_00(1, "x1.0"),
	x1_50(1.5, "x1.5"),
	x2_00(2, "x2.0");

	@Getter double maxPixels;
	@Getter String density;

	private ContentDensity(double maxPixels, String densityStr)
	{
		this.maxPixels = maxPixels;
		this.density = densityStr;
	}

	public static ContentDensity getDensityForSize(Context context)
	{
		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager window = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display display = window.getDefaultDisplay();
		display.getMetrics(displayMetrics);

		int densityName = displayMetrics.densityDpi;

		if (densityName >= DisplayMetrics.DENSITY_LOW && densityName < DisplayMetrics.DENSITY_MEDIUM)
		{
			return ContentDensity.x0_75;
		}
		else if (densityName >= DisplayMetrics.DENSITY_MEDIUM && densityName < DisplayMetrics.DENSITY_TV)
		{
			return ContentDensity.x1_00;
		}
		else if (densityName >= DisplayMetrics.DENSITY_TV && densityName < DisplayMetrics.DENSITY_XHIGH)
		{
			return ContentDensity.x1_50;
		}
		else if (densityName >= DisplayMetrics.DENSITY_XHIGH)
		{
			return ContentDensity.x2_00;
		}

		return x1_00;
	}
}
