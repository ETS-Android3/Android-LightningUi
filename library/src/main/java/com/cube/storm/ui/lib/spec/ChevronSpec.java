package com.cube.storm.ui.lib.spec;

import android.text.TextUtils;

import com.cube.storm.ui.model.list.StandardListItem;
import com.cube.storm.ui.model.property.DestinationLinkProperty;
import com.cube.storm.ui.model.property.LinkProperty;
import com.cube.storm.ui.model.property.ShareLinkProperty;
import com.cube.storm.ui.view.holder.list.StandardListItemViewHolder;

/**
 * Specification abstract class used to determine whether a chevron should be shown on each {@link StandardListItemViewHolder}.
 * Use this abstract class along with {@link com.cube.storm.UiSettings#setChevronSpec(ChevronSpec)} to change the default spec.
 * Currently defaults to {@link ChevronSpec#noChevronSpec()}.
 *
 * @author JR Mitchell
 * @project LightningUI
 */
public abstract class ChevronSpec
{
	/**
	 * Determines whether a chevron should be shown based on the {@link StandardListItem} that it is populated with.
	 *
	 * @param model the model that the {@link StandardListItemViewHolder} is populated with
	 * @return true if the UI element should show a chevron, else false.
	 */
	abstract public boolean shouldChevronShow(StandardListItem model);
	
	/**
	 * Get an instance of {@link ChevronSpec} that never shows chevrons
	 *
	 * @return the new {@link ChevronSpec} instance
	 */
	public static ChevronSpec noChevronSpec()
	{
		return new ChevronSpec()
		{
			@Override public boolean shouldChevronShow(StandardListItem model)
			{
				return false;
			}
		};
	}
	
	/**
	 * Get an instance of {@link ChevronSpec} that shows chevrons in a standard way which matches with iOS Storm behaviour.
	 *
	 * @return the new {@link ChevronSpec} instance
	 */
	public static ChevronSpec standardChevronSpec()
	{
		return new ChevronSpec()
		{
			@Override public boolean shouldChevronShow(StandardListItem model)
			{
				if(model == null)
				{
					return false;
				}
				LinkProperty link = model.getLink();
				return link != null && (
					(link instanceof DestinationLinkProperty && !TextUtils.isEmpty(((DestinationLinkProperty)link).getDestination()))
					|| link instanceof ShareLinkProperty
					//|| link instanceof EmergencyLinkProperty || link instanceof TimerLinkProperty
					//NOTE: the above commented-out LinkProperty types appear to exist in iOS Storm library but not Android Storm library
				);
			}
		};
	}
	
	/**
	 * Get an instance of {@link ChevronSpec} that always shows chevrons
	 *
	 * @return the new {@link ChevronSpec} instance
	 */
	public static ChevronSpec allChevronSpec()
	{
		return new ChevronSpec()
		{
			@Override public boolean shouldChevronShow(StandardListItem model)
			{
				return true;
			}
		};
	}
}
