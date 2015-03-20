package com.cube.storm.ui.lib.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.BuildConfig;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.view.View;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * This is the factory class which is used by Storm to help with getting the correct view holder/controller
 * for a specific view class name.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public abstract class ViewFactory
{
	/**
	 * Gets the view holder class for a specific view name
	 *
	 * @param viewName The name of the view to lookup
	 *
	 * @return The view holder class or null if one was not found.
	 */
	@Nullable
	public Class<? extends ViewHolderFactory> getHolderForView(String viewName)
	{
		try
		{
			return View.valueOf(viewName).getHolderClass();
		}
		catch (IllegalArgumentException e)
		{
			if (BuildConfig.DEBUG)
			{
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Gets the model class for a specific view name
	 *
	 * @param viewName The name of the view to lookup
	 *
	 * @return The view model class or null if one was not found.
	 */
	@Nullable
	public Class<? extends Model> getModelForView(@NonNull String viewName)
	{
		try
		{
			return View.valueOf(viewName).getModelClass();
		}
		catch (IllegalArgumentException e)
		{
			if (BuildConfig.DEBUG)
			{
				e.printStackTrace();
			}
		}

		return null;
	}
}
