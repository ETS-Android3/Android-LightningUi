package com.cube.storm.ui.model.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Accessors(chain = true) @Data
public class SmsLinkProperty extends MessageLinkProperty
{
	public static String CLASS_NAME = "SmsLink";

	{ this.className = CLASS_NAME; }
}
