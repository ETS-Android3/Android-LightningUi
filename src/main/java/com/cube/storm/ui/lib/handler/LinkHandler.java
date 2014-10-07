package com.cube.storm.ui.lib.handler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.lib.parser.ViewParser;
import com.cube.storm.ui.model.page.Page;
import com.cube.storm.ui.model.property.InternalLinkProperty;
import com.cube.storm.ui.model.property.LinkProperty;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

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
			try
			{
				byte[] pageBytes = UiSettings.getInstance().getFileFactory().loadFromUri(context, Uri.parse(((InternalLinkProperty)link).getDestination()));
				Page page = ViewParser.getGson().fromJson(new String(pageBytes, "UTF-8"), Page.class);

				Intent toLoad = UiSettings.getInstance().getIntentFactory().getIntentForPage(context, page);

				if (toLoad != null)
				{
					toLoad.putExtra(StormActivity.EXTRA_PAGE, (Serializable)page);
					context.startActivity(toLoad);
				}
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
	}
}
