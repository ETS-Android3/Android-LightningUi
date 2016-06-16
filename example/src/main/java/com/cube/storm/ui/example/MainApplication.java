package com.cube.storm.ui.example;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.lib.migration.LegacyImageViewProcessor;
import com.cube.storm.ui.lib.resolver.IntentResolver;
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
			.registerIntentResolver(Uri.parse("test://page"), new IntentResolver()
			{
				@Override public Intent resolveIntent(Context context)
				{
					return new Intent(context, ExampleActivity.class);
				}
			})
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
