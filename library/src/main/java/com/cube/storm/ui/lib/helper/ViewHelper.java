package com.cube.storm.ui.lib.helper;

import com.cube.storm.ui.lib.resolver.DefaultViewResolver;
import com.cube.storm.ui.lib.resolver.ViewResolver;
import com.cube.storm.ui.model.list.AnimationListItem;
import com.cube.storm.ui.model.property.DestinationLinkProperty;
import com.cube.storm.ui.model.property.ExternalLinkProperty;
import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.InternalLinkProperty;
import com.cube.storm.ui.model.property.NativeLinkProperty;
import com.cube.storm.ui.model.property.ShareLinkProperty;
import com.cube.storm.ui.model.property.SmsLinkProperty;
import com.cube.storm.ui.model.property.UriLinkProperty;
import com.cube.storm.ui.model.property.VideoProperty;
import com.cube.storm.ui.view.holder.grid.GridItemViewHolder;
import com.cube.storm.ui.view.holder.grid.ImageGridItemViewHolder;
import com.cube.storm.ui.view.holder.grid.StandardGridItemViewHolder;
import com.cube.storm.ui.view.holder.list.AnimationListItemViewHolder;
import com.cube.storm.ui.view.holder.list.AppCollectionItemViewHolder;
import com.cube.storm.ui.view.holder.list.ButtonListItemViewHolder;
import com.cube.storm.ui.view.holder.list.CheckableListItemViewHolder;
import com.cube.storm.ui.view.holder.list.CollectionListItemViewHolder;
import com.cube.storm.ui.view.holder.list.DescriptionListItemViewHolder;
import com.cube.storm.ui.view.holder.list.DividerViewHolder;
import com.cube.storm.ui.view.holder.list.HeaderListItemViewHolder;
import com.cube.storm.ui.view.holder.list.ImageListItemViewHolder;
import com.cube.storm.ui.view.holder.list.ListFooterViewHolder;
import com.cube.storm.ui.view.holder.list.ListHeaderViewHolder;
import com.cube.storm.ui.view.holder.list.LogoListItemViewHolder;
import com.cube.storm.ui.view.holder.list.OrderedListItemViewHolder;
import com.cube.storm.ui.view.holder.list.SpotlightListItemViewHolder;
import com.cube.storm.ui.view.holder.list.StandardListItemViewHolder;
import com.cube.storm.ui.view.holder.list.TextListItemViewHolder;
import com.cube.storm.ui.view.holder.list.TitleListItemViewHolder;
import com.cube.storm.ui.view.holder.list.ToggleableListItemViewHolder;
import com.cube.storm.ui.view.holder.list.UnorderedListItemViewHolder;
import com.cube.storm.ui.view.holder.list.VideoListItemViewHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the enum class with the list of all supported view types, their model classes and their
 * corresponding view holder class. This list should not be modified or overridden
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class ViewHelper
{
	public static Map<String, ViewResolver> getViewResolvers()
	{
		HashMap<String, ViewResolver> views = new HashMap<>();

		/**
		 * Private views - These are not driven by the CMS, these are internal classes derived from
		 * the list model.
		 */
		views.put("_ListHeader", new DefaultViewResolver(com.cube.storm.ui.model.list.List.ListHeader.class, ListHeaderViewHolder.Factory.class));
		views.put("_ListFooter", new DefaultViewResolver(com.cube.storm.ui.model.list.List.ListFooter.class, ListFooterViewHolder.Factory.class));
		views.put("_Divider", new DefaultViewResolver(com.cube.storm.ui.model.list.Divider.class, DividerViewHolder.Factory.class));

		/**
		 * List items
		 */
		views.put("List", new DefaultViewResolver(com.cube.storm.ui.model.list.List.class, null));
		views.put("TextListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.TextListItem.class, TextListItemViewHolder.Factory.class));
		views.put("ImageListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.ImageListItem.class, ImageListItemViewHolder.Factory.class));
		views.put("TitleListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.TitleListItem.class, TitleListItemViewHolder.Factory.class));
		views.put("DescriptionListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.DescriptionListItem.class, DescriptionListItemViewHolder.Factory.class));
		views.put("StandardListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.StandardListItem.class, StandardListItemViewHolder.Factory.class));
		views.put("OrderedListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.OrderedListItem.class, OrderedListItemViewHolder.Factory.class));
		views.put("UnorderedListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.UnorderedListItem.class, UnorderedListItemViewHolder.Factory.class));
		views.put("CheckableListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.CheckableListItem.class, CheckableListItemViewHolder.Factory.class));
		views.put("ButtonListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.ButtonListItem.class, ButtonListItemViewHolder.Factory.class));
		views.put("ToggleableListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.ToggleableListItem.class, ToggleableListItemViewHolder.Factory.class));
		views.put("LogoListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.LogoListItem.class, LogoListItemViewHolder.Factory.class));
		views.put("VideoListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.VideoListItem.class, VideoListItemViewHolder.Factory.class));
		views.put("SpotlightListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.SpotlightListItem.class, SpotlightListItemViewHolder.Factory.class));
		views.put("AnimationListItem", new DefaultViewResolver(AnimationListItem.class, AnimationListItemViewHolder.Factory.class));
		views.put("HeaderListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.HeaderListItem.class, HeaderListItemViewHolder.Factory.class));

		/**
		 * Grid items
		 */
		views.put("Grid", new DefaultViewResolver(com.cube.storm.ui.model.grid.Grid.class, null));
		views.put("GridItem", new DefaultViewResolver(com.cube.storm.ui.model.grid.GridItem.class, GridItemViewHolder.Factory.class));
		views.put("StandardGridItem", new DefaultViewResolver(com.cube.storm.ui.model.grid.StandardGridItem.class, StandardGridItemViewHolder.Factory.class));
		views.put("ImageGridItem", new DefaultViewResolver(com.cube.storm.ui.model.grid.ImageGridItem.class, ImageGridItemViewHolder.Factory.class));

		/**
		 * Collection cells
		 */
		views.put("CollectionListItem", new DefaultViewResolver(com.cube.storm.ui.model.list.collection.CollectionListItem.class, CollectionListItemViewHolder.Factory.class));
		views.put("AppCollectionItem", new DefaultViewResolver(com.cube.storm.ui.model.list.collection.AppCollectionItem.class, AppCollectionItemViewHolder.Factory.class));

		/**
		 * Pages
		 */
		views.put("NativePage", new DefaultViewResolver(com.cube.storm.ui.model.page.NativePage.class, null));
		views.put("ListPage", new DefaultViewResolver(com.cube.storm.ui.model.page.ListPage.class, null));
		views.put("GridPage", new DefaultViewResolver(com.cube.storm.ui.model.page.GridPage.class, null));
		views.put("TabbedPageCollection", new DefaultViewResolver(com.cube.storm.ui.model.page.TabbedPageCollection.class, null));

		/**
		 * Descriptors
		 */
		views.put("PageDescriptor", new DefaultViewResolver(com.cube.storm.ui.model.descriptor.PageDescriptor.class, null));
		views.put("TabbedPageDescriptor", new DefaultViewResolver(com.cube.storm.ui.model.descriptor.TabbedPageDescriptor.class, null));

		/**
		 * Properties
		 */
		views.put("Image", new DefaultViewResolver(ImageProperty.class, null));
		views.put("Video", new DefaultViewResolver(VideoProperty.class, null));
		views.put("DestinationLink", new DefaultViewResolver(DestinationLinkProperty.class, null));
		views.put("InternalLink", new DefaultViewResolver(InternalLinkProperty.class, null));
		views.put("ExternalLink", new DefaultViewResolver(ExternalLinkProperty.class, null));
		views.put("UriLink", new DefaultViewResolver(UriLinkProperty.class, null));
		views.put("ShareLink", new DefaultViewResolver(ShareLinkProperty.class, null));
		views.put("SmsLink", new DefaultViewResolver(SmsLinkProperty.class, null));
		views.put("NativeLink", new DefaultViewResolver(NativeLinkProperty.class, null));

		return views;
	}
}
