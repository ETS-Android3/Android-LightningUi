package com.cube.storm.ui.model.property;

import java.util.ArrayList;

import lombok.Getter;

/**
 * Abstract message model
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public abstract class MessageLinkProperty extends ShareLinkProperty
{
	@Getter private ArrayList<String> recipients;
}
