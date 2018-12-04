package com.cube.storm.ui.model.grid;

import android.os.Parcel;

import com.cube.storm.ui.model.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @Project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public abstract class GridItem extends Model
{
	public static String CLASS_NAME = "GridItem";

	{ this.className = CLASS_NAME; }

	protected Boolean spanned;

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{

	}
}
