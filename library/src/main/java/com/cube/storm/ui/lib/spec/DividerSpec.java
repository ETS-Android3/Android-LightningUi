package com.cube.storm.ui.lib.spec;

import android.support.annotation.Nullable;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.list.ListItem;

import java.util.List;

/**
 * Specification interface used to creating dividers inbetween items in {@link com.cube.storm.ui.controller.adapter.StormListAdapter}.
 * <p/>
 * Use this interface along with {@link com.cube.storm.UiSettings#setDividerSpec(DividerSpec)} to change the default spec. Currently
 * defaults to {@link com.cube.storm.ui.lib.spec.ListDividerSpec} which will only display a divider in between {@link com.cube.storm.ui.model.list.List}
 * objects (but not the last one in the list) and in between {@link com.cube.storm.ui.model.list.StandardListItem} objects.
 * <p/>
 * The default divider used is {@link com.cube.storm.ui.model.list.Divider}, you can implement your own by adding it to your overridden {@link com.cube.storm.ui.lib.factory.ViewFactory}
 * and returning it in your overridden {@link com.cube.storm.ui.lib.spec.DividerSpec}.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public interface DividerSpec
{
	@Nullable
	public ListItem shouldAddDivider(int position, List<Model> items);
}
