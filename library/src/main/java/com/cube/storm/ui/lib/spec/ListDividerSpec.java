package com.cube.storm.ui.lib.spec;

import androidx.annotation.Nullable;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.list.Divider;
import com.cube.storm.ui.model.list.ListItem;
import com.cube.storm.ui.model.list.StandardListItem;

import java.util.List;

/**
 * Default {@link com.cube.storm.ui.lib.spec.DividerSpec} for {@link com.cube.storm.ui.controller.adapter.StormListAdapter}
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class ListDividerSpec implements DividerSpec
{
	@Nullable
	@Override public ListItem shouldAddDivider(int position, List<Model> items)
	{
		if (position >= 0 && position < items.size() - 1)
		{
			if (items.get(position) instanceof com.cube.storm.ui.model.list.List.ListHeader)
			{
				return null;
			}

			if (position + 1 < items.size() && items.get(position + 1) instanceof StandardListItem)
			{
				return new Divider();
			}
		}

		return null;
	}
}
