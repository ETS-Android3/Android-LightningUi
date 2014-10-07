package com.cube.storm.ui.model.property;

import android.os.Parcel;
import android.text.TextUtils;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.data.ContentDensity;

public class BundleImageProperty extends ImageProperty
{
	protected ImageDescriptorProperty src;

	@Override public String getSrc()
	{
		if (UiSettings.getInstance().getContentDensity() == ContentDensity.x0_75 && !TextUtils.isEmpty(src.getX075()))
		{
			return src.getX075();
		}
		else if (UiSettings.getInstance().getContentDensity() == ContentDensity.x1_00 && !TextUtils.isEmpty(src.getX1()))
		{
			return src.getX1();
		}
		else if (UiSettings.getInstance().getContentDensity() == ContentDensity.x1_50 && !TextUtils.isEmpty(src.getX15()))
		{
			return src.getX15();
		}
		else if (UiSettings.getInstance().getContentDensity() == ContentDensity.x2_00 && !TextUtils.isEmpty(src.getX2()))
		{
			return src.getX2();
		}
		else
		{
			return src.getX1();
		}
	}

	@Override public String getFallbackSrc()
	{
		return src.getX1();
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel parcel, int i)
	{

	}
}
