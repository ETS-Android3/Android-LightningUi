package com.cube.storm.ui.model.page;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.property.TextProperty;

import java.util.Collection;

import lombok.Getter;

/**
 * Abstract page class
 *
 * @author Callum Taylor
 * @project StormUI
 */
public abstract class Page extends Model
{
	@Getter protected TextProperty title;
	@Getter protected String name;

	public abstract Collection<? extends Model> getChildren();
}
