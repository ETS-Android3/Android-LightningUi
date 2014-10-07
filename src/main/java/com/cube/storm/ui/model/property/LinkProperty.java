package com.cube.storm.ui.model.property;

import lombok.Getter;

/**
 * Base abstract link property class. This class is never instantiated directly
 *
 * @author Callum Taylor
 * @project StormUI
 */
public abstract class LinkProperty extends Property
{
	@Getter protected TextProperty title;
}
