package com.cube.storm.ui.lib.spec;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.list.ListItem;

import java.util.List;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public interface DividerSpec
{
	public ListItem shouldAddDivider(int position, List<Model> items);
}
