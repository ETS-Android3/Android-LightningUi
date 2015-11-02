package com.cube.storm.ui.lib.migration;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.ui.lib.factory.ViewFactory;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.property.AnimationImageProperty;
import com.cube.storm.ui.model.property.DestinationLinkProperty;
import com.cube.storm.ui.model.property.ExternalLinkProperty;
import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.InternalLinkProperty;
import com.cube.storm.ui.model.property.NativeLinkProperty;
import com.cube.storm.ui.model.property.ShareLinkProperty;
import com.cube.storm.ui.model.property.SpotlightImageProperty;
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
import com.cube.storm.ui.view.holder.list.SpotlightListItemViewHolder;
import com.cube.storm.ui.view.holder.list.StandardListItemViewHolder;
import com.cube.storm.ui.view.holder.list.TextListItemViewHolder;
import com.cube.storm.ui.view.holder.list.TitleListItemViewHolder;
import com.cube.storm.ui.view.holder.list.ToggleableListItemViewHolder;
import com.cube.storm.ui.view.holder.list.UnorderedListItemViewHolder;
import com.cube.storm.ui.view.holder.list.VideoListItemViewHolder;

/**
 * Converts from legacy storm view names to new storm view names. Override the viewfactory in {@link com.cube.storm.UiSettings}
 * by calling {@link com.cube.storm.UiSettings.Builder#viewFactory(ViewFactory)} with this class
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class LegacyViewFactory extends ViewFactory
{
	public enum LegacyView
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
		GroupView(com.cube.storm.ui.model.list.List.class, null),
		TextListItemView(com.cube.storm.ui.model.list.TextListItem.class, TextListItemViewHolder.Factory.class),
		ImageListItemView(com.cube.storm.ui.model.list.ImageListItem.class, ImageListItemViewHolder.Factory.class),
		ListItemView(com.cube.storm.ui.model.list.TitleListItem.class, TitleListItemViewHolder.Factory.class),
		DescriptionListItemView(com.cube.storm.ui.model.list.DescriptionListItem.class, DescriptionListItemViewHolder.Factory.class),
		StandardListItemView(com.cube.storm.ui.model.list.StandardListItem.class, StandardListItemViewHolder.Factory.class),
		AnnotatedListItemView(com.cube.storm.ui.model.list.OrderedListItem.class, OrderedListItemViewHolder.Factory.class),
		BulletListItemView(com.cube.storm.ui.model.list.UnorderedListItem.class, UnorderedListItemViewHolder.Factory.class),
		CheckableListItemView(com.cube.storm.ui.model.list.CheckableListItem.class, CheckableListItemViewHolder.Factory.class),
		ButtonListItemView(com.cube.storm.ui.model.list.ButtonListItem.class, ButtonListItemViewHolder.Factory.class),
		ToggleableListItemView(com.cube.storm.ui.model.list.ToggleableListItem.class, ToggleableListItemViewHolder.Factory.class),
		LogoListItemView(com.cube.storm.ui.model.list.LogoListItem.class, LogoListItemViewHolder.Factory.class),
		VideoListItem(com.cube.storm.ui.model.list.VideoListItem.class, VideoListItemViewHolder.Factory.class),
		SpotlightImageListItemView(com.cube.storm.ui.model.list.SpotlightListItem.class, SpotlightListItemViewHolder.Factory.class),
		AnimatedImageListItemView(com.cube.storm.ui.model.list.AnimatedImageListItem.class, AnimatedImageListItemViewHolder.Factory.class),
		HeaderListItemView(com.cube.storm.ui.model.list.HeaderListItem.class, HeaderListItemViewHolder.Factory.class),
		GroupedTextListItemView(com.cube.storm.ui.model.list.DescriptionListItem.class, DescriptionListItemViewHolder.Factory.class),

		/**
		 * Grid items
		 */
		GridView(com.cube.storm.ui.model.grid.Grid.class, null),
		GridCell(com.cube.storm.ui.model.grid.GridItem.class, GridItemViewHolder.Factory.class),
		StandardGridCell(com.cube.storm.ui.model.grid.StandardGridItem.class, StandardGridItemViewHolder.Factory.class),
		ImageGridCell(com.cube.storm.ui.model.grid.ImageGridItem.class, ImageGridItemViewHolder.Factory.class),

		/**
		 * Collection cells
		 */
		CollectionListItemView(com.cube.storm.ui.model.list.collection.CollectionListItem.class, CollectionListItemViewHolder.Factory.class),
		AppCollectionCell(com.cube.storm.ui.model.list.collection.AppCollectionItem.class, AppCollectionItemViewHolder.Factory.class),

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
		Image(ImageProperty.class, null),
		NativeImage(ImageProperty.class, null),
		Icon(ImageProperty.class, null),
		AnimationImage(AnimationImageProperty.class, null),
		SpotlightImage(SpotlightImageProperty.class, null),
		DestinationLink(DestinationLinkProperty.class, null),
		InternalLink(InternalLinkProperty.class, null),
		ExternalLink(ExternalLinkProperty.class, null),
		UriLink(UriLinkProperty.class, null),
		ShareLink(ShareLinkProperty.class, null),
		NativeLink(NativeLinkProperty.class, null);

		private Class<? extends Model> model;
		private Class<? extends ViewHolderFactory> holder;

		private LegacyView(Class<? extends Model> model, Class<? extends ViewHolderFactory> holder)
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

	@Nullable @Override public Class<? extends Model> getModelForView(@NonNull String viewName)
	{
		try
		{
			return LegacyView.valueOf(viewName).getModelClass();
		}
		catch (Exception e)
		{
			return super.getModelForView(viewName);
		}
	}

	@Nullable @Override public Class<? extends ViewHolderFactory> getHolderForView(String viewName)
	{
		try
		{
			return LegacyView.valueOf(viewName).getHolderClass();
		}
		catch (Exception e)
		{
			return super.getHolderForView(viewName);
		}
	}
}
