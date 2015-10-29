package com.cube.storm.ui.model;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

/**
 * Base model class for all Storm objects
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public abstract class Model implements Serializable/*, Parcelable*/
{
	@Getter protected String id;
	@SerializedName("class") @Getter protected String className;

	public abstract int describeContents();
	public abstract void writeToParcel(Parcel dest, int flags);
}
