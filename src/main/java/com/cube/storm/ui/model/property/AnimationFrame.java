package com.cube.storm.ui.model.property;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;

import java.util.ArrayList;

import lombok.Getter;

/**
 * Animation frame class that has an array of {@link com.cube.storm.ui.model.property.ImageProperty} and a delay used
 * in {@link com.cube.storm.ui.model.property.AnimationImageProperty}
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class AnimationFrame extends Model
{
	@Getter private ArrayList<ImageProperty> image;
	@Getter private long delay;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
