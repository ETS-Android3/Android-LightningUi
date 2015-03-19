package com.cube.storm.ui.view;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.storm.UiSettings;
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
	public static void populate(ViewGroup embeddedLinksContainer, Iterable<LinkProperty> linkProperties)
	{
		if (linkProperties != null)
		{
			embeddedLinksContainer.removeAllViews();

			for (LinkProperty linkProperty : linkProperties)
			{
				final LinkProperty property = linkProperty;
				View embeddedLinkView = LayoutInflater.from(embeddedLinksContainer.getContext()).inflate(R.layout.button_embedded_link, embeddedLinksContainer, false);

				if (embeddedLinkView != null)
				{
					Button button = (Button)embeddedLinkView.findViewById(R.id.button);
					button.setVisibility(View.GONE);
					String embeddedContent = UiSettings.getInstance().getTextProcessor().process(linkProperty.getTitle().getContent());

					if (!TextUtils.isEmpty(embeddedContent))
					{
						button.setText(embeddedContent);
						button.setVisibility(View.VISIBLE);
					}

					button.setOnClickListener(new View.OnClickListener()
					{
						@Override public void onClick(View v)
						{
							UiSettings.getInstance().getLinkHandler().handleLink(v.getContext(), property);
						}
					});

					embeddedLinksContainer.setVisibility(View.VISIBLE);
					embeddedLinksContainer.addView(button);
				}
			}
		}
	}
}
