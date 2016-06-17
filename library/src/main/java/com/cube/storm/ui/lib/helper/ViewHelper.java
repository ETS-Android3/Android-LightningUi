package com.cube.storm.ui.lib.helper;

import com.cube.storm.ui.lib.resolver.DefaultViewResolver;
import com.cube.storm.ui.lib.resolver.ViewResolver;
import com.cube.storm.ui.model.descriptor.PageDescriptor;
import com.cube.storm.ui.model.descriptor.TabbedPageDescriptor;
import com.cube.storm.ui.model.grid.Grid;
import com.cube.storm.ui.model.grid.GridItem;
import com.cube.storm.ui.model.grid.ImageGridItem;
import com.cube.storm.ui.model.grid.StandardGridItem;
import com.cube.storm.ui.model.list.AnimationListItem;
import com.cube.storm.ui.model.list.ButtonListItem;
import com.cube.storm.ui.model.list.CheckableListItem;
import com.cube.storm.ui.model.list.DescriptionListItem;
import com.cube.storm.ui.model.list.Divider;
import com.cube.storm.ui.model.list.HeaderListItem;
import com.cube.storm.ui.model.list.ImageListItem;
import com.cube.storm.ui.model.list.List;
import com.cube.storm.ui.model.list.LogoListItem;
import com.cube.storm.ui.model.list.OrderedListItem;
import com.cube.storm.ui.model.list.SpotlightListItem;
import com.cube.storm.ui.model.list.StandardListItem;
import com.cube.storm.ui.model.list.TextListItem;
import com.cube.storm.ui.model.list.TitleListItem;
import com.cube.storm.ui.model.list.ToggleableListItem;
import com.cube.storm.ui.model.list.UnorderedListItem;
import com.cube.storm.ui.model.list.VideoListItem;
import com.cube.storm.ui.model.list.collection.AppCollectionItem;
import com.cube.storm.ui.model.list.collection.CollectionListItem;
import com.cube.storm.ui.model.page.GridPage;
import com.cube.storm.ui.model.page.ListPage;
import com.cube.storm.ui.model.page.NativePage;
import com.cube.storm.ui.model.page.TabbedPageCollection;
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
		views.put(List.ListHeader.CLASS_NAME, new DefaultViewResolver(List.ListHeader.class, ListHeaderViewHolder.Factory.class));
		views.put(List.ListFooter.CLASS_NAME, new DefaultViewResolver(List.ListFooter.class, ListFooterViewHolder.Factory.class));
		views.put(Divider.CLASS_NAME, new DefaultViewResolver(Divider.class, DividerViewHolder.Factory.class));

		/**
		 * List items
		 */
		views.put(List.CLASS_NAME, new DefaultViewResolver(List.class, null));
		views.put(TextListItem.CLASS_NAME, new DefaultViewResolver(TextListItem.class, TextListItemViewHolder.Factory.class));
		views.put(ImageListItem.CLASS_NAME, new DefaultViewResolver(ImageListItem.class, ImageListItemViewHolder.Factory.class));
		views.put(TitleListItem.CLASS_NAME, new DefaultViewResolver(TitleListItem.class, TitleListItemViewHolder.Factory.class));
		views.put(DescriptionListItem.CLASS_NAME, new DefaultViewResolver(DescriptionListItem.class, DescriptionListItemViewHolder.Factory.class));
		views.put(StandardListItem.CLASS_NAME, new DefaultViewResolver(StandardListItem.class, StandardListItemViewHolder.Factory.class));
		views.put(OrderedListItem.CLASS_NAME, new DefaultViewResolver(OrderedListItem.class, OrderedListItemViewHolder.Factory.class));
		views.put(UnorderedListItem.CLASS_NAME, new DefaultViewResolver(UnorderedListItem.class, UnorderedListItemViewHolder.Factory.class));
		views.put(CheckableListItem.CLASS_NAME, new DefaultViewResolver(CheckableListItem.class, CheckableListItemViewHolder.Factory.class));
		views.put(ButtonListItem.CLASS_NAME, new DefaultViewResolver(ButtonListItem.class, ButtonListItemViewHolder.Factory.class));
		views.put(ToggleableListItem.CLASS_NAME, new DefaultViewResolver(ToggleableListItem.class, ToggleableListItemViewHolder.Factory.class));
		views.put(LogoListItem.CLASS_NAME, new DefaultViewResolver(LogoListItem.class, LogoListItemViewHolder.Factory.class));
		views.put(VideoListItem.CLASS_NAME, new DefaultViewResolver(VideoListItem.class, VideoListItemViewHolder.Factory.class));
		views.put(SpotlightListItem.CLASS_NAME, new DefaultViewResolver(SpotlightListItem.class, SpotlightListItemViewHolder.Factory.class));
		views.put(AnimationListItem.CLASS_NAME, new DefaultViewResolver(AnimationListItem.class, AnimationListItemViewHolder.Factory.class));
		views.put(HeaderListItem.CLASS_NAME, new DefaultViewResolver(HeaderListItem.class, HeaderListItemViewHolder.Factory.class));

		/**
		 * Grid items
		 */
		views.put(Grid.CLASS_NAME, new DefaultViewResolver(Grid.class, null));
		views.put(GridItem.CLASS_NAME, new DefaultViewResolver(GridItem.class, GridItemViewHolder.Factory.class));
		views.put(StandardGridItem.CLASS_NAME, new DefaultViewResolver(StandardGridItem.class, StandardGridItemViewHolder.Factory.class));
		views.put(ImageGridItem.CLASS_NAME, new DefaultViewResolver(ImageGridItem.class, ImageGridItemViewHolder.Factory.class));

		/**
		 * Collection cells
		 */
		views.put(CollectionListItem.CLASS_NAME, new DefaultViewResolver(CollectionListItem.class, CollectionListItemViewHolder.Factory.class));
		views.put(AppCollectionItem.CLASS_NAME, new DefaultViewResolver(AppCollectionItem.class, AppCollectionItemViewHolder.Factory.class));

		/**
		 * Pages
		 */
		views.put(NativePage.CLASS_NAME, new DefaultViewResolver(NativePage.class, null));
		views.put(ListPage.CLASS_NAME, new DefaultViewResolver(ListPage.class, null));
		views.put(GridPage.CLASS_NAME, new DefaultViewResolver(GridPage.class, null));
		views.put(TabbedPageCollection.CLASS_NAME, new DefaultViewResolver(TabbedPageCollection.class, null));

		/**
		 * Descriptors
		 */
		views.put(PageDescriptor.CLASS_NAME, new DefaultViewResolver(PageDescriptor.class, null));
		views.put(TabbedPageDescriptor.CLASS_NAME, new DefaultViewResolver(TabbedPageDescriptor.class, null));

		/**
		 * Properties
		 */
		views.put(ImageProperty.CLASS_NAME, new DefaultViewResolver(ImageProperty.class, null));
		views.put(VideoProperty.CLASS_NAME, new DefaultViewResolver(VideoProperty.class, null));
		views.put(DestinationLinkProperty.CLASS_NAME, new DefaultViewResolver(DestinationLinkProperty.class, null));
		views.put(InternalLinkProperty.CLASS_NAME, new DefaultViewResolver(InternalLinkProperty.class, null));
		views.put(ExternalLinkProperty.CLASS_NAME, new DefaultViewResolver(ExternalLinkProperty.class, null));
		views.put(UriLinkProperty.CLASS_NAME, new DefaultViewResolver(UriLinkProperty.class, null));
		views.put(ShareLinkProperty.CLASS_NAME, new DefaultViewResolver(ShareLinkProperty.class, null));
		views.put(SmsLinkProperty.CLASS_NAME, new DefaultViewResolver(SmsLinkProperty.class, null));
		views.put(NativeLinkProperty.CLASS_NAME, new DefaultViewResolver(NativeLinkProperty.class, null));

		return views;
	}
}
