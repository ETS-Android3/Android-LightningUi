package com.cube.storm.ui.view;

/**
 * This is the enum class with the list of all supported view types, their model classes and their
 * corresponding view holder class. This list should not be modified or overridden
 *
 * @author Callum Taylor
 * @project Storm Test
 */
public enum View
{
	Default(Object.class, Object.class);

	public Class model;
	public Class holder;

	private View(Class model, Class holder)
	{
		this.model = model;
		this.holder = holder;
	}
}
