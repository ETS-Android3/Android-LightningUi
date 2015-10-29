package com.cube.storm.ui.model.page;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.property.TextProperty;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Abstract page class
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public abstract class Page extends Model
{
	protected TextProperty title;
	protected String name;

	public abstract Collection<? extends Model> getChildren();
}
