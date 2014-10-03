package com.cube.storm.ui.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

/**
 * Base model class for all Storm objects
 *
 * @author Callum Taylor
 * @project StormUI
 */
public abstract class Model implements Serializable, Parcelable
{
	@SerializedName("class") @Getter protected String className;
}
