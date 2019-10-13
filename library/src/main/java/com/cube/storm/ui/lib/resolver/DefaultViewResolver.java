package com.cube.storm.ui.lib.resolver;

import androidx.annotation.Nullable;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * Basic implementation of view resolver
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public class DefaultViewResolver extends ViewResolver
{
	protected Class<? extends Model> model;
	protected Class<? extends ViewHolderFactory> viewHolder;

	public DefaultViewResolver(Class<? extends Model> model, Class<? extends ViewHolderFactory> viewHolder)
	{
		this.model = model;
		this.viewHolder = viewHolder;
	}

	@Nullable @Override public Class<? extends Model> resolveModel()
	{
		return model;
	}

	@Nullable @Override public Class<? extends ViewHolderFactory> resolveViewHolder()
	{
		return viewHolder;
	}
}
