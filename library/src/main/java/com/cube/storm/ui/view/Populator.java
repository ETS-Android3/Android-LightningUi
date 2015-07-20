package com.cube.storm.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.ui.R;
import com.cube.storm.ui.model.property.LinkProperty;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @Project LightningUi
 */
public abstract class Populator
{
	public static void populate(ViewGroup embeddedLinksContainer, Iterable<? extends LinkProperty> linkProperties)
	{
		if (linkProperties != null)
		{
			embeddedLinksContainer.removeAllViews();

			for (LinkProperty linkProperty : linkProperties)
			{
				View embeddedLinkView = LayoutInflater.from(embeddedLinksContainer.getContext()).inflate(R.layout.button_embedded_link, embeddedLinksContainer, false);

				if (embeddedLinkView != null)
				{
					Button button = (Button)embeddedLinkView.findViewById(R.id.button);
					button.populate(linkProperty);
					embeddedLinksContainer.setVisibility(View.VISIBLE);
					embeddedLinksContainer.addView(button);
				}
			}
		}
	}
}
