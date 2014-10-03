package com.cube.storm.ui.view;

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
	TextListItem(com.cube.storm.ui.model.list.TextListItem.class, TextListItemHolder.class),

	// Not used
	Default(Object.class, Object.class);

	private Class model;
	private Class holder;

	private View(Class model, Class holder)
	{
		this.model = model;
		this.holder = holder;
	}

	/**
	 * @return Gets the holder class of the view
	 */
	public Class getHolderClass()
	{
		return holder;
	}

	/**
	 * @return Gets the model class of the view
	 */
	public Class getModelClass()
	{
		return model;
	}
}
