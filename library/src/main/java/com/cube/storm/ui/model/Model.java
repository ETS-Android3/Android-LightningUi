package com.cube.storm.ui.model;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Base model class for all Storm objects
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public abstract class Model implements Serializable/*, Parcelable*/
{
	protected String id;
	@SerializedName("class") protected String className;

	public abstract int describeContents();
	public abstract void writeToParcel(Parcel dest, int flags);
}
