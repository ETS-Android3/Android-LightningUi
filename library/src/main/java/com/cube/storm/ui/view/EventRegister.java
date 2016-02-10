package com.cube.storm.ui.view;

import com.cube.storm.ui.event.Event;
import com.cube.storm.ui.model.list.ListItem;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @Project LightningUi
 */
public abstract class EventRegister
{
	public static void register(ListItem model, android.view.View view)
	{
		if (model.getEvents() != null)
		{
			for (Event event : model.getEvents())
			{
//				event.register(model, view);
			}
		}
	}
}
