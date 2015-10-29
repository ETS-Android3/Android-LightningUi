package com.cube.storm.ui.model.page;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.descriptor.PageDescriptor;

import java.util.Collection;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public abstract class PageCollection extends Model
{
	public abstract Collection<? extends PageDescriptor> getPages();
}
