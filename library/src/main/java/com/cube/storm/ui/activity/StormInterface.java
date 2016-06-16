package com.cube.storm.ui.activity;

/**
 * Interface used in {@link StormActivity} and {@link com.cube.storm.ui.fragment.StormFragment} that is used when loading
 * files. Allows for classes to follow a specific flow of method calls for overriding
 *
 * @author Callum Taylor
 */
public interface StormInterface
{
	/**
	 * Called when laying out the activity/fragment
	 * @return The layout resource ID
	 */
	public int getLayoutResource();

	/**
	 * Called when loading the page from the provided EXTRA_URI key
	 * @param pageUri The page URI to load
	 */
	public void loadPage(String pageUri);

	/**
	 * Called when the load failed, or a missing argument was provided
	 */
	public void onLoadFail();
}
