package com.cube.storm.ui;

/**
 * This is the entry point class of the library. To enable the use of the library, you must instantiate
 * a new {@link com.cube.storm.ui.UiSettings.Builder} object in your {@link android.app.Application} singleton class.
 *
 * This class should not be directly instantiated.
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class UiSettings
{
	/**
	 * The singleton instance of the settings
	 */
	private static UiSettings instance;

	/**
	 * Gets the instance of the {@link UiSettings} class
	 * Throws a {@link java.lang.IllegalAccessError} if the singleton has not been instantiated properly
	 *
	 * @return The instance
	 */
	public static UiSettings getInstance()
	{
		if (instance == null)
		{
			throw new IllegalAccessError("You must build the Ui settings object first using UiSettings$Builder");
		}

		return instance;
	}

	public static class Builder
	{

	}
}
