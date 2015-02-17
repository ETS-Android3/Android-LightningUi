package com.cube.storm.ui.view;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.view.holder.ViewHolderController;
import com.cube.storm.ui.view.holder.grid.GridItemHolder;
import com.cube.storm.ui.view.holder.grid.ImageGridItemHolder;
import com.cube.storm.ui.view.holder.grid.StandardGridItemHolder;
import com.cube.storm.ui.view.holder.list.AnimatedImageListItemHolder;
import com.cube.storm.ui.view.holder.list.AppCollectionItemHolder;
import com.cube.storm.ui.view.holder.list.ButtonListItemHolder;
import com.cube.storm.ui.view.holder.list.CheckableListItemHolder;
import com.cube.storm.ui.view.holder.list.CollectionListItemHolder;
import com.cube.storm.ui.view.holder.list.DescriptionListItemHolder;
import com.cube.storm.ui.view.holder.list.DividerHolder;
import com.cube.storm.ui.view.holder.list.HeaderListItemHolder;
import com.cube.storm.ui.view.holder.list.ImageListItemHolder;
import com.cube.storm.ui.view.holder.list.ListFooterHolder;
import com.cube.storm.ui.view.holder.list.ListHeaderHolder;
import com.cube.storm.ui.view.holder.list.LogoListItemHolder;
import com.cube.storm.ui.view.holder.list.OrderedListItemHolder;
import com.cube.storm.ui.view.holder.list.SpotlightImageListItemHolder;
import com.cube.storm.ui.view.holder.list.StandardListItemHolder;
import com.cube.storm.ui.view.holder.list.TextListItemHolder;
import com.cube.storm.ui.view.holder.list.TitleListItemHolder;
import com.cube.storm.ui.view.holder.list.ToggleableListItemHolder;
import com.cube.storm.ui.view.holder.list.UnorderedListItemHolder;
import com.cube.storm.ui.view.holder.list.VideoListItemHolder;

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
	_ListHeader(com.cube.storm.ui.model.list.List.ListHeader.class, ListHeaderHolder.class),
	_ListFooter(com.cube.storm.ui.model.list.List.ListFooter.class, ListFooterHolder.class),
	_Divider(com.cube.storm.ui.model.list.Divider.class, DividerHolder.class),

	/**
	 * List items
	 */
	List(com.cube.storm.ui.model.list.List.class, null),
	TextListItem(com.cube.storm.ui.model.list.TextListItem.class, TextListItemHolder.class),
	ImageListItem(com.cube.storm.ui.model.list.ImageListItem.class, ImageListItemHolder.class),
	TitleListItem(com.cube.storm.ui.model.list.TitleListItem.class, TitleListItemHolder.class),
	DescriptionListItem(com.cube.storm.ui.model.list.DescriptionListItem.class, DescriptionListItemHolder.class),
	StandardListItem(com.cube.storm.ui.model.list.StandardListItem.class, StandardListItemHolder.class),
	OrderedListItem(com.cube.storm.ui.model.list.OrderedListItem.class, OrderedListItemHolder.class),
	UnorderedListItem(com.cube.storm.ui.model.list.UnorderedListItem.class, UnorderedListItemHolder.class),
	CheckableListItem(com.cube.storm.ui.model.list.CheckableListItem.class, CheckableListItemHolder.class),
	ButtonListItem(com.cube.storm.ui.model.list.ButtonListItem.class, ButtonListItemHolder.class),
	ToggleableListItem(com.cube.storm.ui.model.list.ToggleableListItem.class, ToggleableListItemHolder.class),
	LogoListItem(com.cube.storm.ui.model.list.LogoListItem.class, LogoListItemHolder.class),
	VideoListItem(com.cube.storm.ui.model.list.VideoListItem.class, VideoListItemHolder.class),
	SpotlightImageListItem(com.cube.storm.ui.model.list.SpotlightImageListItem.class, SpotlightImageListItemHolder.class),
	AnimatedImageListItem(com.cube.storm.ui.model.list.AnimatedImageListItem.class, AnimatedImageListItemHolder.class),
	HeaderListItem(com.cube.storm.ui.model.list.HeaderListItem.class, HeaderListItemHolder.class),

	/**
	 * Grid items
	 */
	Grid(com.cube.storm.ui.model.grid.Grid.class, null),
	GridItem(com.cube.storm.ui.model.grid.GridItem.class, GridItemHolder.class),
	StandardGridItem(com.cube.storm.ui.model.grid.StandardGridItem.class, StandardGridItemHolder.class),
	ImageGridItem(com.cube.storm.ui.model.grid.ImageGridItem.class, ImageGridItemHolder.class),

	/**
	 * Collection cells
	 */
	CollectionListItem(com.cube.storm.ui.model.list.collection.CollectionListItem.class, CollectionListItemHolder.class),
	AppCollectionItem(com.cube.storm.ui.model.list.collection.AppCollectionItem.class, AppCollectionItemHolder.class),

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
	Image(com.cube.storm.ui.model.property.BundleImageProperty.class, null),
	NativeImage(com.cube.storm.ui.model.property.NativeImageProperty.class, null),
	Icon(com.cube.storm.ui.model.property.BundleImageProperty.class, null),
	AnimationImage(com.cube.storm.ui.model.property.AnimationImageProperty.class, null),
	SpotlightImage(com.cube.storm.ui.model.property.SpotlightImageProperty.class, null),
	DestinationLink(com.cube.storm.ui.model.property.DestinationLinkProperty.class, null),
	InternalLink(com.cube.storm.ui.model.property.InternalLinkProperty.class, null),
	ExternalLink(com.cube.storm.ui.model.property.ExternalLinkProperty.class, null),
	UriLink(com.cube.storm.ui.model.property.UriLinkProperty.class, null),
	ShareLink(com.cube.storm.ui.model.property.ShareLinkProperty.class, null),
	NativeLink(com.cube.storm.ui.model.property.NativeLinkProperty.class, null);

	private Class<? extends Model> model;
	private Class<? extends ViewHolderController> holder;

	private View(Class<? extends Model> model, Class<? extends ViewHolderController> holder)
	{
		this.model = model;
		this.holder = holder;
	}

	/**
	 * @return Gets the holder class of the view
	 */
	public Class<? extends ViewHolderController> getHolderClass()
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
