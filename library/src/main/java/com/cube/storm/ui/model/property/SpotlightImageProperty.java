package com.cube.storm.ui.model.property;

import com.cube.storm.ui.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class SpotlightImageProperty extends AnimationImageProperty
{
	{ this.className = View.SpotlightImage.name(); }

	protected TextProperty text;
	protected LinkProperty link;
}
