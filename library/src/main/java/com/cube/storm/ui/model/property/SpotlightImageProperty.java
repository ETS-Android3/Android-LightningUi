package com.cube.storm.ui.model.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Image property for {@link com.cube.storm.ui.model.list.SpotlightListItem}. Contains text to be overlaid on the image,
 * a link to push to when a specific frame is clicked, a delay, and the image to display.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public class SpotlightImageProperty extends AnimationFrame
{
	protected TextProperty text;
	protected LinkProperty link;
}
