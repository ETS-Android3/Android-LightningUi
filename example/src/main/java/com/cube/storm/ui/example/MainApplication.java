package com.cube.storm.ui.example;

import android.app.Application;
import android.net.Uri;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.example.provider.CustomIntentProvider;
import com.cube.storm.ui.fragment.StormStaticFragment;
import com.cube.storm.ui.lib.migration.LegacyImageViewProcessor;
import com.cube.storm.ui.lib.migration.LegacyViews;
import com.cube.storm.ui.lib.provider.DefaultIntentProvider;
import com.cube.storm.ui.lib.resolver.DefaultIntentResolver;
import com.cube.storm.ui.model.App;
import com.cube.storm.ui.model.property.ImageProperty;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainApplication extends Application
{
	@Override public void onCreate()
	{
		super.onCreate();

		// Initiate settings
		UiSettings uiSettings = new UiSettings.Builder(this)
			.registerType(new TypeToken<ArrayList<ImageProperty>>(){}.getType(), new LegacyImageViewProcessor())
			.registerIntentResolver(Uri.parse("assets://pages/3.json"), new DefaultIntentResolver(CustomStormActivity.class, StormStaticFragment.class))
			.registerIntentProvider(new CustomIntentProvider())
			.registerIntentProvider(new DefaultIntentProvider())
			.build();

		// Loading app json
		String appUri = "assets://app.json";
		App app = UiSettings.getInstance().getViewBuilder().buildApp(Uri.parse(appUri));

		if (app != null)
		{
			UiSettings.getInstance().setApp(app);
		}
	}
}
