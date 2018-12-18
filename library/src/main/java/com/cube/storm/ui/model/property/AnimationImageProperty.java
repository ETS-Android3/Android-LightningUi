package com.cube.storm.ui.model.property;

import android.os.Parcel;

import com.cube.storm.ui.model.list.AnimationListItem;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Animation frame wrapper used in {@link AnimationListItem}
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
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
