package com.cube.storm;

import com.cube.storm.ui.lib.factory.IntentFactory;
import com.cube.storm.ui.lib.factory.ViewFactory;

import lombok.Getter;

/**
 * This is the entry point class of the library. To enable the use of the library, you must instantiate
 * a new {@link UiSettings.Builder} object in your {@link android.app.Application} singleton class.
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

	/**
	 * Default private constructor
	 */
	private UiSettings(){}

	/**
	 * The intent factory instance of the module. This is the instance that will be used to resolve
	 * every activity/fragment for a storm page/Uri
	 */
	@Getter private IntentFactory intentFactory;

	/**
	 * The view factory instance of the module. This is the instance that will be used to resolve
	 * models and holders for a specific view
	 */
	@Getter private ViewFactory viewFactory;

	/**
	 * The builder class for {@link com.cube.storm.UiSettings}. Use this to create a new {@link com.cube.storm.UiSettings} instance
	 * with the customised properties specific for your project.
	 *
	 * Call {@link #build()} to build the settings object.
	 */
	public static class Builder
	{
		/**
		 * The temporary instance of the {@link UiSettings} object.
		 */
		private UiSettings construct;

		/**
		 * Default constructor
		 */
		public Builder()
		{
			construct = new UiSettings();

			intentFactory(new IntentFactory());
			viewFactory(new ViewFactory());
		}

		/**
		 * Sets the default {@link com.cube.storm.ui.lib.factory.IntentFactory} for the module
		 *
		 * @param intentFactory The new {@link com.cube.storm.ui.lib.factory.IntentFactory}
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder intentFactory(IntentFactory intentFactory)
		{
			construct.intentFactory = intentFactory;
			return this;
		}

		/**
		 * Sets the default {@link com.cube.storm.ui.lib.factory.ViewFactory} for the module
		 *
		 * @param viewFactory The new {@link com.cube.storm.ui.lib.factory.ViewFactory}
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder viewFactory(ViewFactory viewFactory)
		{
			construct.viewFactory = viewFactory;
			return this;
		}

		/**
		 * Builds the final settings object and sets its instance. Use {@link #getInstance()} to retrieve the settings
		 * instance.
		 *
		 * @return The newly set {@link com.cube.storm.UiSettings} instance
		 */
		public UiSettings build()
		{
			return (UiSettings.instance = construct);
		}
	}
}
