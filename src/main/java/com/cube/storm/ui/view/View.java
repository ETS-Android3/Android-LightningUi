package com.cube.storm.ui.view;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.view.holder.Holder;
import com.cube.storm.ui.view.holder.TextListItemHolder;

/**
 * This is the enum class with the list of all supported view types, their model classes and their
 * corresponding view holder class. This list should not be modified or overridden
 *
 * @author Callum Taylor
 * @project Storm Test
 */
public enum View
{
	TextListItem(com.cube.storm.ui.model.list.TextListItem.class, TextListItemHolder.class);

	private Class<? extends Model> model;
	private Class<? extends Holder> holder;

	private View(Class<? extends Model> model, Class<? extends Holder> holder)
	{
		this.model = model;
		this.holder = holder;
	}

	/**
	 * @return Gets the holder class of the view
	 */
	public Class<? extends Holder> getHolderClass()
	{
		return holder;
	}

	/**
	 * @return Gets the model class of the view
	 */
	public Class<? extends Model> getModelClass()
	{
		return model;
	}
}
