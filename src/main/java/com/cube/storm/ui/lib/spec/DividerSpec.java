package com.cube.storm.ui.lib.spec;

import android.support.annotation.Nullable;

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
	@Nullable
	public ListItem shouldAddDivider(int position, List<Model> items);
}
