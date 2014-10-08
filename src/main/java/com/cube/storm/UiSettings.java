package com.cube.storm;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.cube.storm.ui.data.ContentDensity;
import com.cube.storm.ui.lib.factory.FileFactory;
import com.cube.storm.ui.lib.factory.IntentFactory;
import com.cube.storm.ui.lib.factory.ViewFactory;
import com.cube.storm.ui.lib.handler.LinkHandler;
import com.cube.storm.ui.lib.parser.ViewBuilder;
import com.cube.storm.ui.lib.parser.ViewProcessor;
import com.cube.storm.ui.lib.resolver.AssetsResolver;
import com.cube.storm.ui.lib.resolver.FileResolver;
import com.cube.storm.ui.model.App;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.list.ListItem;
import com.cube.storm.ui.model.page.Page;
import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.util.lib.resolver.Resolver;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.IOException;
import java.io.InputStream;
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
	 * App data for the content
	 */
	@Getter private App app;

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
	 * The view processor map used by {@link com.cube.storm.ui.lib.parser.ViewBuilder}. Use {@link com.cube.storm.UiSettings.Builder#registerType(Class, com.cube.storm.ui.lib.parser.ViewProcessor)} to
	 * override the processor used to match models with json class names
	 */
	@Getter private Map<Class, ViewProcessor> viewProcessors = new LinkedHashMap<Class, ViewProcessor>(0);

	/**
	 * Image loader which is used when displaying images in the list
	 */
	@Getter private ImageLoader imageLoader = ImageLoader.getInstance();

	/**
	 * The density to use when loading images
	 */
	@Getter private ContentDensity contentDensity;

	/**
	 * The handler used when a link is triggered
	 */
	@Getter private LinkHandler linkHandler;

	/**
	 * The gson builder class used to build all of the storm objects from json/string/binary
	 */
	@Getter private ViewBuilder viewBuilder;

	/**
	 * Uri resolver used to load a file based on it's protocol. You should not need to use this instance
	 * directly to load a file, instead use {@link com.cube.storm.ui.lib.factory.FileFactory} which uses this
	 * to resolve a file and load it. Only use this if you want to load a specific scheme
	 */
	@Getter private Map<String, Resolver> uriResolvers = new LinkedHashMap<String, Resolver>(2);

	/**
	 * Sets the app model of the content
	 *
	 * @param app The new app model
	 */
	public void setApp(@NonNull App app)
	{
		this.app = app;
	}

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
			imageLoaderConfiguration(new ImageLoaderConfiguration.Builder(context));
			linkHandler(new LinkHandler());

			contentDensity(ContentDensity.x1_00);

			ViewProcessor<? extends Model> baseProcessor = new ViewProcessor<Model>()
			{
				@Override public Class<? extends Model> getClassFromName(String name)
				{
					return UiSettings.getInstance().getViewFactory().getModelForView(name);
				}
			};

			registerType(Page.class, baseProcessor);
			registerType(ListItem.class, baseProcessor);
			registerType(ImageProperty.class, baseProcessor);
			registerType(LinkProperty.class, baseProcessor);

			registerUriResolver("file", new FileResolver());
			registerUriResolver("assets", new AssetsResolver(context));

			viewBuilder(new ViewBuilder());
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
		 * Sets the default image loader configuration.
		 *
		 * Note: The ImageDownloader set in the builder is overriden by this method to allow the use
		 * of {@link #getUriResolvers()} to resolve the uris for loading images. Use {@link #registerUriResolver(String, com.cube.storm.util.lib.resolver.Resolver)}
		 * to register any additional custom uris you wish to override.
		 *
		 * TODO: Find a better way to allow users to use their own imagedownloader, we should not be blocking this config
		 *
		 * @param configuration The new configuration for the image loader
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder imageLoaderConfiguration(ImageLoaderConfiguration.Builder configuration)
		{
			if (construct.imageLoader.isInited())
			{
				construct.imageLoader.destroy();
			}

			configuration.imageDownloader(new BaseImageDownloader(context)
			{
				@Override public InputStream getStream(String imageUri, Object extra) throws IOException
				{
					// Loop through the resolvers to resolve the file
					Uri fileUri = Uri.parse(imageUri);

					if (!TextUtils.isEmpty(fileUri.getScheme()))
					{
						for (String protocol : UiSettings.getInstance().getUriResolvers().keySet())
						{
							if (protocol.equalsIgnoreCase(fileUri.getScheme()))
							{
								imageUri = UiSettings.getInstance().getUriResolvers().get(protocol).resolveUri(fileUri).toString();
							}
						}
					}

					return super.getStream(imageUri, extra);
				}
			});

			construct.imageLoader.init(configuration.build());
			return this;
		}

		/**
		 * Sets the default {@link com.cube.storm.ui.data.ContentDensity} for the module
		 *
		 * @param contentDensity The new {@link com.cube.storm.ui.data.ContentDensity}
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder contentDensity(ContentDensity contentDensity)
		{
			construct.contentDensity = contentDensity;
			return this;
		}

		/**
		 * Sets the default {@link com.cube.storm.ui.lib.handler.LinkHandler} for the module
		 *
		 * @param linkHandler The new {@link com.cube.storm.ui.lib.handler.LinkHandler}
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder linkHandler(LinkHandler linkHandler)
		{
			construct.linkHandler = linkHandler;
			return this;
		}

		/**
		 * Sets the default {@link com.cube.storm.ui.lib.parser.ViewBuilder} for the module
		 *
		 * @param viewBuilder The new {@link com.cube.storm.ui.lib.parser.ViewBuilder}
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder viewBuilder(ViewBuilder viewBuilder)
		{
			construct.viewBuilder = viewBuilder;
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
		 * Registers a uri resolver to use in the {@link com.cube.storm.ui.lib.factory.FileFactory}
		 *
		 * @param protocol The string protocol to register
		 * @param resolver The resolver to use for the registered protocol
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder registerUriResolver(String protocol, Resolver resolver)
		{
			construct.uriResolvers.put(protocol, resolver);
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
