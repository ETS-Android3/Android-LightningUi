package com.cube.storm.ui.example;

import android.os.Bundle;

import com.cube.storm.ui.activity.StormActivity;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 */
public class CustomStormActivity extends StormActivity
{
	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setTitle("");
	}

	@Override public void onLoadFail()
	{

	}

	@Override public void setTitle(CharSequence title)
	{
		super.setTitle("This is an overridden!");
	}
}
