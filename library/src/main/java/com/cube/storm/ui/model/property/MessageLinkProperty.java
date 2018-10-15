package com.cube.storm.ui.model.property;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Abstract message model
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data
public abstract class MessageLinkProperty extends ShareLinkProperty
{
	protected ArrayList<String> recipients;
}
