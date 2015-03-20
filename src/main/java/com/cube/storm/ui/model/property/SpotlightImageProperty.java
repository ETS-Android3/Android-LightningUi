package com.cube.storm.ui.model.property;

import lombok.Getter;

/**
 * Image property for {@link com.cube.storm.ui.model.list.SpotlightListItem}. Contains text to be overlaid on the image,
 * a link to push to when a specific frame is clicked, a delay, and the image to display.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class SpotlightImageProperty extends AnimationFrame
{
	@Getter private TextProperty text;
	@Getter private LinkProperty link;
}
