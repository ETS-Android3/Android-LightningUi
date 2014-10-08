package com.cube.storm.ui.lib.handler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.model.property.InternalLinkProperty;
import com.cube.storm.ui.model.property.LinkProperty;

/**
 * Link handler class used when a link is triggered in a holder
 *
 * @author Callum Taylor
 * @project StormUI
 */
public class LinkHandler
{
	/**
	 * Called to handle when a link is clicked
	 *
	 * @param context The context of the view clicked
	 * @param link The link property that was clicked
	 */
	public void handleLink(Context context, LinkProperty link)
	{
		if (link instanceof InternalLinkProperty)
		{
			Intent toLoad = UiSettings.getInstance().getIntentFactory().geIntentForPageUri(context, Uri.parse(((InternalLinkProperty)link).getDestination()));

			if (toLoad != null)
			{
				context.startActivity(toLoad);
			}
		}
	}
}
