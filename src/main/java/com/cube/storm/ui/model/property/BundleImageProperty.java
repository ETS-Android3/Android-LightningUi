package com.cube.storm.ui.model.property;

import android.os.Parcel;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.data.ContentSize;

import java.util.Arrays;
import java.util.Comparator;

public class BundleImageProperty extends ImageProperty
{
	protected ImageDescriptorProperty[] src;

	@Override public String getSrc()
	{
		Arrays.sort(src, new ImageDescriptorPropertyComparator());
		if (UiSettings.getInstance().getContentSize() == ContentSize.SMALL)
		{
			return src[0].getSrc().getDestination();
		}
		else if (UiSettings.getInstance().getContentSize() == ContentSize.MEDIUM)
		{
			return src[(int)src.length/2].getSrc().getDestination();
		}
		else if (UiSettings.getInstance().getContentSize() == ContentSize.LARGE)
		{
			return src[(int)src.length-1].getSrc().getDestination();
		}

		else
		{
			return src[(int)src.length/2].getSrc().getDestination();
		}
	}

	@Override public String getFallbackSrc()
	{
		Arrays.sort(src, new ImageDescriptorPropertyComparator());
		return src[(int)src.length/2].getSrc().getDestination();
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel parcel, int i)
	{

	}

	private class ImageDescriptorPropertyComparator implements Comparator<ImageDescriptorProperty>
	{
		@Override public int compare(ImageDescriptorProperty lhs, ImageDescriptorProperty rhs)
		{
			long totalArea = lhs.getDimensions().getHeight() * lhs.getDimensions().getWidth();
			return Long.valueOf(totalArea).compareTo(Long.valueOf(rhs.getDimensions().getHeight() * rhs.getDimensions().getWidth()));
		}
	}
}
