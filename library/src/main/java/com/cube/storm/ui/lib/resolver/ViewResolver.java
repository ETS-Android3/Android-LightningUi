package com.cube.storm.ui.lib.resolver;

import android.support.annotation.Nullable;

import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.view.holder.ViewHolderFactory;

/**
 * View Resolver class used to link class names with models and views parsed from Storm Content pages.
 *
 * @author Callum Taylor
 * @project LightningUi
 */
public abstract class ViewResolver
{
	@Nullable
	public abstract Class<? extends Model> resolveModel();

	@Nullable
	public abstract Class<? extends ViewHolderFactory> resolveViewHolder();
}
