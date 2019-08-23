package com.cube.storm.ui.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.cube.storm.UiSettings;

/**
 * @author Callum Taylor
 * @project LightningUtil
 */
public class ExampleActivity extends AppCompatActivity
{
	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// Load the initial page
		Intent start = UiSettings.getInstance().getIntentFactory().getIntentForPageUri(this, Uri.parse(UiSettings.getInstance().getApp().getVector()));

		if (start != null)
		{
			startActivity(start);
			finish();
		}
	}
}
