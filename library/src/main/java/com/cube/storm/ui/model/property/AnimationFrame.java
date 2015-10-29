package com.cube.storm.ui.model.property;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Animation frame class that has an array of {@link com.cube.storm.ui.model.property.ImageProperty} and a delay used
 * in {@link com.cube.storm.ui.model.property.AnimationImageProperty}
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class AnimationFrame extends Model
{
	protected ArrayList<ImageProperty> image;
	protected long delay;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
