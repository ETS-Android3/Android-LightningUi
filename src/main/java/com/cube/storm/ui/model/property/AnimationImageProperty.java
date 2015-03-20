package com.cube.storm.ui.model.property;

import android.os.Parcel;

import java.util.ArrayList;

import lombok.Getter;

/**
 * Animation frame wrapper used in {@link com.cube.storm.ui.model.list.AnimatedImageListItem}
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class AnimationImageProperty extends Property
{
	@Getter protected boolean looped;
	@Getter protected ArrayList<AnimationFrame> frames;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
