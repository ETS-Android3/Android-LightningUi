package com.cube.storm.ui.model.page;

import android.support.annotation.NonNull;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.property.TextProperty;

import java.util.Collection;

import lombok.Getter;

/**
 * Abstract page class
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public abstract class Page extends Model
{
	@Getter protected TextProperty title;
	@Getter protected String name;

	public Page(@NonNull String title, @NonNull String name)
	{
		this.title = new TextProperty(title);
		this.name = name;
	}

	public abstract Collection<? extends Model> getChildren();
}
