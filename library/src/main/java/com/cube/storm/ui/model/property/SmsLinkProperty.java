package com.cube.storm.ui.model.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public class SmsLinkProperty extends MessageLinkProperty
{
	public static String CLASS_NAME = "SmsLink";

	{ this.className = CLASS_NAME; }
}
