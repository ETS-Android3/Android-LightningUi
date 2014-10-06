package com.cube.storm;

import android.content.Context;

import com.cube.storm.ui.lib.factory.FileFactory;
import com.cube.storm.ui.lib.factory.IntentFactory;
import com.cube.storm.ui.lib.factory.ViewFactory;
import com.cube.storm.ui.lib.parser.ViewProcessor;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.list.ListItem;
import com.cube.storm.ui.model.page.Page;

import java.util.LinkedHashMap;
import java.util.Map;

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
	 * Factory class responsible for loading a file from disk based on its Uri
	 */
	@Getter private FileFactory fileFactory;

	/**
	 * The view processor map used by {@link com.cube.storm.ui.lib.parser.ViewParser}. Use {@link com.cube.storm.UiSettings.Builder#registerType(Class, com.cube.storm.ui.lib.parser.ViewProcessor)} to
	 * override the processor used to match models with json class names
	 */
	@Getter private Map<Class, ViewProcessor> viewProcessors = new LinkedHashMap<Class, ViewProcessor>(0);

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

		private Context context;

		/**
		 * Default constructor
		 */
		public Builder(Context context)
		{
			this.construct = new UiSettings();
			this.context = context;

			intentFactory(new IntentFactory(){});
			viewFactory(new ViewFactory(){});
			fileFactory(new FileFactory(){});

			ViewProcessor<? extends Model> baseProcessor = new ViewProcessor<Model>()
			{
				@Override public Class<? extends Model> getClassFromName(String name)
				{
					return UiSettings.getInstance().getViewFactory().getModelForView(name);
				}
			};

			registerType(Page.class, baseProcessor);
			registerType(ListItem.class, baseProcessor);
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
		 * Sets the default {@link com.cube.storm.ui.lib.factory.FileFactory} for the module
		 *
		 * @param fileFactory The new {@link com.cube.storm.ui.lib.factory.FileFactory}
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder fileFactory(FileFactory fileFactory)
		{
			construct.fileFactory = fileFactory;
			return this;
		}

		/**
		 * Registers a deserializer type for a class instance. Use this method to override what processor
		 * gets used for a specific view type.
		 *
		 * @param instanceClass The class to register for deserialization
		 * @param deserializer The processor class
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder registerType(Class instanceClass, ViewProcessor deserializer)
		{
			construct.viewProcessors.put(instanceClass, deserializer);
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
