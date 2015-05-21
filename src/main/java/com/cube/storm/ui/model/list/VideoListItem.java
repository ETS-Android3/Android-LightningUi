package com.cube.storm.ui.model.list;

import android.os.Parcel;

import com.cube.storm.ui.model.property.VideoProperty;
import com.cube.storm.ui.view.View;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A view model with a video collection property
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class VideoListItem extends ImageListItem
{
	{ this.className = View.VideoListItem.name(); }

	protected Collection<? extends VideoProperty> videos;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
