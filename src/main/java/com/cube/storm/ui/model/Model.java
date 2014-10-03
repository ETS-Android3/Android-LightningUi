package com.cube.storm.ui.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public abstract class Model implements Serializable, Parcelable
{
	@SerializedName("class") protected String className;
}
