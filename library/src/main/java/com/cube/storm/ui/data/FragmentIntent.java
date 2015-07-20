package com.cube.storm.ui.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

/**
 * FragmentIntent is a package class for hosting details about a fragment. It works in a similar
 * fashion to {@link android.content.Intent}.
 *
 * The class is used by Storm activities when determining what fragment to use for a Page.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@Data
public class FragmentIntent implements Parcelable
{
	private Class<?> fragment;
	private String title;
	private Bundle arguments;

	public FragmentIntent(){}

	public FragmentIntent(Class<?> fragment, Bundle arguments)
	{
		this.fragment = fragment;
		this.arguments = arguments;
	}

	public FragmentIntent(Class<?> fragment, String title, Bundle arguments)
	{
		this.fragment = fragment;
		this.title = title;
		this.arguments = arguments;
	}

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(fragment.getName());
		dest.writeString(title);
		dest.writeBundle(arguments);
	}

	private void readFromParcel(Parcel dest)
	{
		try
		{
			this.fragment = Class.forName(dest.readString());
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		this.title = dest.readString();
		this.arguments = dest.readBundle();
	}

	public static Creator<FragmentIntent> CREATOR = new Creator<FragmentIntent>()
	{
		@Override public FragmentIntent createFromParcel(Parcel source)
		{
			FragmentIntent intent = new FragmentIntent();
			intent.readFromParcel(source);
			return intent;
		}

		@Override public FragmentIntent[] newArray(int size)
		{
			return new FragmentIntent[size];
		}
	};
}
