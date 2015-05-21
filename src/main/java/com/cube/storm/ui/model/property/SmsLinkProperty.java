package com.cube.storm.ui.model.property;

import com.cube.storm.ui.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public class SmsLinkProperty extends MessageLinkProperty
{
	{ this.className = View.SmsLink.name(); }
}
