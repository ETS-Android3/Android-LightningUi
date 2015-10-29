package com.cube.storm.ui.model.property;

import android.os.Parcel;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Animation frame wrapper used in {@link com.cube.storm.ui.model.list.AnimatedImageListItem}
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class AnimationImageProperty extends Property
{
	private boolean looped;
	private ArrayList<AnimationFrame> frames;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
