package com.cube.storm;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.cube.storm.ui.controller.downloader.StormSchemeHandler;
import com.cube.storm.ui.data.ContentSize;
import com.cube.storm.ui.lib.factory.FileFactory;
import com.cube.storm.ui.lib.factory.IntentFactory;
import com.cube.storm.ui.lib.handler.LinkHandler;
import com.cube.storm.ui.lib.helper.ViewHelper;
import com.cube.storm.ui.lib.parser.ViewBuilder;
import com.cube.storm.ui.lib.parser.ViewProcessor;
import com.cube.storm.ui.lib.processor.TextProcessor;
import com.cube.storm.ui.lib.resolver.AppResolver;
import com.cube.storm.ui.lib.resolver.IntentResolver;
import com.cube.storm.ui.lib.resolver.IntentResolverMap;
import com.cube.storm.ui.lib.resolver.ViewResolver;
import com.cube.storm.ui.lib.spec.DividerSpec;
import com.cube.storm.ui.lib.spec.ListDividerSpec;
import com.cube.storm.ui.model.App;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.descriptor.PageDescriptor;
import com.cube.storm.ui.model.list.ListItem;
import com.cube.storm.ui.model.list.collection.CollectionItem;
import com.cube.storm.ui.model.page.Page;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.model.property.TextProperty;
import com.cube.storm.util.lib.processor.Processor;
import com.cube.storm.util.lib.resolver.AssetsResolver;
import com.cube.storm.util.lib.resolver.FileResolver;
import com.cube.storm.util.lib.resolver.Resolver;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.handlers.SchemeHandler;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * This is the entry point class of the library. To enable the use of the library, you must instantiate
 * a new {@link UiSettings.Builder} object in your {@link android.app.Application} singleton class.
 *
 * This class should not be directly instantiated.
 *
 * @author Callum Taylor
 * @project LightningUi
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
	@Getter @Setter private IntentFactory intentFactory;

	/**
	 * Intent resolver used to resolving specific pages to an fragment/intent using either a page ID/Uri/descriptor.
	 * Intents registered here are prioritised over default resolutions from {@link #intentFactory}
	 */
	@Getter @Setter private IntentResolverMap intentResolver = new IntentResolverMap();

	/**
	 * Factory class responsible for loading a file from disk based on its Uri
	 */
	@Getter @Setter private FileFactory fileFactory;

	/**
	 * The view processor map used by {@link com.cube.storm.ui.lib.parser.ViewBuilder}. Use {@link com.cube.storm.UiSettings.Builder#registerType(Type, com.cube.storm.ui.lib.parser.ViewProcessor)} to
	 * override the processor used to match models with json class names
	 */
	@Getter @Setter private Map<Type, ViewProcessor> viewProcessors = new LinkedHashMap<Type, ViewProcessor>(0);

	/**
	 * Image loader which is used when displaying images in the list
	 */
	@Getter @Setter private ImageLoader imageLoader = ImageLoader.getInstance();

	/**
	 * The density to use when loading images
	 */
	@Getter @Setter private ContentSize contentSize;

	/**
	 * The handler used when a link is triggered
	 */
	@Getter @Setter private LinkHandler linkHandler;

	/**
	 * The gson builder class used to build all of the storm objects from json/string/binary
	 */
	@Getter @Setter private ViewBuilder viewBuilder;

	/**
	 * Processor class used to process strings as part of {@link com.cube.storm.ui.model.property.TextProperty}
	 */
	@Getter @Setter private Processor<TextProperty, String> textProcessor;

	/**
	 * Uri resolver used to load a file based on it's protocol. You should not need to use this instance
	 * directly to load a file, instead use {@link com.cube.storm.ui.lib.factory.FileFactory} which uses this
	 * to resolve a file and load it. Only use this if you want to load a specific scheme
	 */
	@Getter @Setter private Map<String, Resolver> uriResolvers = new HashMap<String, Resolver>(2);

	/**
	 * Maps class names with a view resolver object to resolve models/viewholders.
	 */
	@Getter @Setter private Map<String, ViewResolver> viewResolvers = new HashMap<String, ViewResolver>(2);

	/**
	 * Default divider spec to use in {@link com.cube.storm.ui.controller.adapter.StormListAdapter}
	 */
	@Getter @Setter private DividerSpec dividerSpec;

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
			this.context = context.getApplicationContext();

			intentFactory(new IntentFactory(){});
			fileFactory(new FileFactory(){});
			imageLoaderConfiguration(new ImageLoaderConfiguration.Builder(this.context));
			linkHandler(new LinkHandler());
			textProcessor(new TextProcessor());

			contentSize(ContentSize.AUTO);

			// Register views and models
			registerViewResolver(ViewHelper.getViewResolvers());

			// Register view resolvers for Gson adapters
			ViewProcessor<? extends Model> baseProcessor = new ViewProcessor<Model>()
			{
				@Override public Class<? extends Model> getClassFromName(String name)
				{
					ViewResolver resolver = UiSettings.getInstance().getViewResolvers().get(name);

					if (resolver != null)
					{
						return resolver.resolveModel();
					}

					return null;
				}
			};

			registerType(Page.class, baseProcessor);
			registerType(ListItem.class, baseProcessor);
			registerType(CollectionItem.class, baseProcessor);
			registerType(LinkProperty.class, baseProcessor);

			registerUriResolver("file", new FileResolver());
			registerUriResolver("assets", new AssetsResolver(this.context));
			registerUriResolver("app", new AppResolver(this.context));

			viewBuilder(new ViewBuilder(){});
			dividerSpec(new ListDividerSpec());
		}

		/**
		 * Sets the default {@link com.cube.storm.ui.lib.spec.DividerSpec} for the list adapter to use when layout out its children
		 *
		 * @param spec The new divider spec to use by default
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder dividerSpec(DividerSpec spec)
		{
			construct.dividerSpec = spec;
			return this;
		}

		/**
		 * Registers a page id/name to resolve to a specific intent resolver.
		 *
		 * @param pageId The id of the page. This will also match on a page's `name`
		 * @param resolver The intent resolver class
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder registerIntentResolver(String pageId, IntentResolver resolver)
		{
			construct.getIntentResolver().registerPageId(pageId, resolver);
			return this;
		}

		/**
		 * Registers a page URI to resolve to a specific intent resolver.
		 *
		 * @param pageUri The URI of the page.
		 * @param resolver The intent resolver class
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder registerIntentResolver(Uri pageUri, IntentResolver resolver)
		{
			construct.getIntentResolver().registerPageUri(pageUri, resolver);
			return this;
		}

		/**
		 * Registers a page descriptor to resolve to a specific intent resolver.
		 *
		 * @param pageDescriptor The descriptor of the page.
		 * @param resolver The intent resolver class
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder registerIntentResolver(PageDescriptor pageDescriptor, IntentResolver resolver)
		{
			construct.getIntentResolver().registerPageDescriptor(pageDescriptor, resolver);
			return this;
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
		 * @param configuration The new configuration for the image loader
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder imageLoaderConfiguration(ImageLoaderConfiguration.Builder configuration)
		{
			// Retain existing handlers if any exist
			Map<String, SchemeHandler> handlers = null;
			if (construct.imageLoader.isInited())
			{
				handlers = construct.imageLoader.getRegisteredSchemeHandlers();
				construct.imageLoader.destroy();
			}

			construct.imageLoader.init(configuration.build());

			if (handlers != null && handlers.size() > 0)
			{
				for (String key : handlers.keySet())
				{
					construct.imageLoader.registerSchemeHandler(key, handlers.get(key));
				}
			}

			return this;
		}

		/**
		 * Sets the default {@link com.cube.storm.ui.data.ContentSize} for the module
		 *
		 * @param contentSize The new {@link com.cube.storm.ui.data.ContentSize}
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder contentSize(ContentSize contentSize)
		{
			construct.contentSize = contentSize;
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
		 * Sets the default {@link com.cube.storm.util.lib.processor.Processor} for the module
		 *
		 * @param textProcessor The new {@link com.cube.storm.util.lib.processor.Processor}
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder textProcessor(Processor<TextProperty, String> textProcessor)
		{
			construct.textProcessor = textProcessor;
			return this;
		}

		/**
		 * Registers a view resolver for matching class name with model and viewholder. Use this method to set what class
		 * gets used for a specific view/model.
		 *
		 * @param viewName The name of the view to register
		 * @param resolver The view resolver class
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder registerViewResolver(String viewName, ViewResolver resolver)
		{
			construct.viewResolvers.put(viewName, resolver);
			return this;
		}

		/**
		 * Registers a view resolver for matching class name with model and viewholder. Use this method to set what class
		 * gets used for a specific view/model.
		 *
		 * @param resolvers The map of view resolver classes
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder registerViewResolver(Map<String, ViewResolver> resolvers)
		{
			construct.viewResolvers.putAll(resolvers);
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
		public Builder registerType(Type instanceClass, ViewProcessor deserializer)
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
			if (!ImageLoader.getInstance().getRegisteredSchemeHandlers().containsKey(protocol))
			{
				ImageLoader.getInstance().registerSchemeHandler(protocol, new StormSchemeHandler());
			}
			return this;
		}

		/**
		 * Registers a uri resolvers
		 *
		 * @param resolvers The map of resolvers to register
		 *
		 * @return The {@link com.cube.storm.UiSettings.Builder} instance for chaining
		 */
		public Builder registerUriResolver(Map<String, Resolver> resolvers)
		{
			construct.uriResolvers.putAll(resolvers);
			for (String protocol : resolvers.keySet())
			{
				if (!ImageLoader.getInstance().getRegisteredSchemeHandlers().containsKey(protocol))
				{
					ImageLoader.getInstance().registerSchemeHandler(protocol, new StormSchemeHandler());
				}
			}
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
