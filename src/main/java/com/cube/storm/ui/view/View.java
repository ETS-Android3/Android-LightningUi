package com.cube.storm.ui.view;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.property.DestinationLinkProperty;
import com.cube.storm.ui.model.property.ExternalLinkProperty;
import com.cube.storm.ui.model.property.InternalLinkProperty;
import com.cube.storm.ui.model.property.NativeLinkProperty;
import com.cube.storm.ui.model.property.ShareLinkProperty;
import com.cube.storm.ui.model.property.UriLinkProperty;
import com.cube.storm.ui.view.holder.ViewHolderFactory;
import com.cube.storm.ui.view.holder.grid.GridItemViewHolder;
import com.cube.storm.ui.view.holder.grid.ImageGridItemViewHolder;
import com.cube.storm.ui.view.holder.grid.StandardGridItemViewHolder;
import com.cube.storm.ui.view.holder.list.AnimatedImageListItemViewHolder;
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
import com.cube.storm.ui.view.holder.list.SpotlightImageListItemViewHolder;
import com.cube.storm.ui.view.holder.list.StandardListItemViewHolder;
import com.cube.storm.ui.view.holder.list.TextListItemViewHolder;
import com.cube.storm.ui.view.holder.list.TitleListItemViewHolder;
import com.cube.storm.ui.view.holder.list.ToggleableListItemViewHolder;
import com.cube.storm.ui.view.holder.list.UnorderedListItemViewHolder;
import com.cube.storm.ui.view.holder.list.VideoListItemViewHolder;

/**
 * This is the enum class with the list of all supported view types, their model classes and their
 * corresponding view holder class. This list should not be modified or overridden
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public enum View
{
	/**
	 * Private views - These are not driven by the CMS, these are internal classes derived from
	 * the list model.
 	 */
	_ListHeader(com.cube.storm.ui.model.list.List.ListHeader.class, ListHeaderViewHolder.Factory.class),
	_ListFooter(com.cube.storm.ui.model.list.List.ListFooter.class, ListFooterViewHolder.Factory.class),
	_Divider(com.cube.storm.ui.model.list.Divider.class, DividerViewHolder.Factory.class),

	/**
	 * List items
	 */
	List(com.cube.storm.ui.model.list.List.class, null),
	TextListItem(com.cube.storm.ui.model.list.TextListItem.class, TextListItemViewHolder.Factory.class),
	ImageListItem(com.cube.storm.ui.model.list.ImageListItem.class, ImageListItemViewHolder.Factory.class),
	TitleListItem(com.cube.storm.ui.model.list.TitleListItem.class, TitleListItemViewHolder.Factory.class),
	DescriptionListItem(com.cube.storm.ui.model.list.DescriptionListItem.class, DescriptionListItemViewHolder.Factory.class),
	StandardListItem(com.cube.storm.ui.model.list.StandardListItem.class, StandardListItemViewHolder.Factory.class),
	OrderedListItem(com.cube.storm.ui.model.list.OrderedListItem.class, OrderedListItemViewHolder.Factory.class),
	UnorderedListItem(com.cube.storm.ui.model.list.UnorderedListItem.class, UnorderedListItemViewHolder.Factory.class),
	CheckableListItem(com.cube.storm.ui.model.list.CheckableListItem.class, CheckableListItemViewHolder.Factory.class),
	ButtonListItem(com.cube.storm.ui.model.list.ButtonListItem.class, ButtonListItemViewHolder.Factory.class),
	ToggleableListItem(com.cube.storm.ui.model.list.ToggleableListItem.class, ToggleableListItemViewHolder.Factory.class),
	LogoListItem(com.cube.storm.ui.model.list.LogoListItem.class, LogoListItemViewHolder.Factory.class),
	VideoListItem(com.cube.storm.ui.model.list.VideoListItem.class, VideoListItemViewHolder.Factory.class),
	SpotlightListItem(com.cube.storm.ui.model.list.SpotlightListItem.class, SpotlightImageListItemViewHolder.Factory.class),
	AnimationListItem(com.cube.storm.ui.model.list.AnimationListItem.class, AnimatedImageListItemViewHolder.Factory.class),
	HeaderListItem(com.cube.storm.ui.model.list.HeaderListItem.class, HeaderListItemViewHolder.Factory.class),

	/**
	 * Grid items
	 */
	Grid(com.cube.storm.ui.model.grid.Grid.class, null),
	GridItem(com.cube.storm.ui.model.grid.GridItem.class, GridItemViewHolder.Factory.class),
	StandardGridItem(com.cube.storm.ui.model.grid.StandardGridItem.class, StandardGridItemViewHolder.Factory.class),
	ImageGridItem(com.cube.storm.ui.model.grid.ImageGridItem.class, ImageGridItemViewHolder.Factory.class),

	/**
	 * Collection cells
	 */
	CollectionListItem(com.cube.storm.ui.model.list.collection.CollectionListItem.class, CollectionListItemViewHolder.Factory.class),
	AppCollectionItem(com.cube.storm.ui.model.list.collection.AppCollectionItem.class, AppCollectionItemViewHolder.Factory.class),

	/**
	 * Pages
	 */
	ListPage(com.cube.storm.ui.model.page.ListPage.class, null),
	GridPage(com.cube.storm.ui.model.page.GridPage.class, null),
	TabbedPageCollection(com.cube.storm.ui.model.page.TabbedPageCollection.class, null),

	/**
	 * Descriptors
	 */
	PageDescriptor(com.cube.storm.ui.model.descriptor.PageDescriptor.class, null),
	TabbedPageDescriptor(com.cube.storm.ui.model.descriptor.TabbedPageDescriptor.class, null),

	/**
	 * Properties
	 */
	DestinationLink(DestinationLinkProperty.class, null),
	InternalLink(InternalLinkProperty.class, null),
	ExternalLink(ExternalLinkProperty.class, null),
	UriLink(UriLinkProperty.class, null),
	ShareLink(ShareLinkProperty.class, null),
	NativeLink(NativeLinkProperty.class, null);

	private Class<? extends Model> model;
	private Class<? extends ViewHolderFactory> holder;

	private View(Class<? extends Model> model, Class<? extends ViewHolderFactory> holder)
	{
		this.model = model;
		this.holder = holder;
	}

	/**
	 * @return Gets the holder class of the view
	 */
	public Class<? extends ViewHolderFactory> getHolderClass()
	{
		return holder;
	}

	/**
	 * @return Gets the model class of the view
	 */
	public Class<? extends Model> getModelClass()
	{
		return model;
	}
}
