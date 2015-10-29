package com.cube.storm.ui.model.property;

import com.cube.storm.ui.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Image property for {@link com.cube.storm.ui.model.list.SpotlightListItem}. Contains text to be overlaid on the image,
 * a link to push to when a specific frame is clicked, a delay, and the image to display.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class SpotlightImageProperty extends AnimationFrame
{
	{ this.className = View.SpotlightImage.name(); }

	protected TextProperty text;
	protected LinkProperty link;
}
