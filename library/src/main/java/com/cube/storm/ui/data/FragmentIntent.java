package com.cube.storm.ui.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * FragmentIntent is a package class for hosting details about a fragment. It works in a similar
 * fashion to {@link android.content.Intent}.
 *
 * The class is used by Storm activities when determining what fragment to use for a Page.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@Data @EqualsAndHashCode(callSuper=false)
public class FragmentIntent implements Parcelable
{
	@NonNull private Class<?> fragment = Object.class;
	@NonNull private String title = "";
	@NonNull private Bundle arguments = new Bundle();

	public FragmentIntent(){}

	public FragmentIntent(@NonNull Class<?> fragment)
	{
		this.fragment = fragment;
	}

	public FragmentIntent(@NonNull Class<?> fragment, @NonNull Bundle arguments)
	{
		this.fragment = fragment;
		this.arguments = arguments;
	}

	public FragmentIntent(@NonNull Class<?> fragment, @NonNull String title)
	{
		this.fragment = fragment;
		this.title = title;
	}

	public FragmentIntent(@NonNull Class<?> fragment, @NonNull String title, @NonNull Bundle arguments)
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
		this.arguments = dest.readBundle(getClass().getClassLoader());
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
