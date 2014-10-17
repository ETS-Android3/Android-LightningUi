package com.cube.storm.ui.data;

import com.cube.storm.ui.model.descriptor.PageDescriptor;

import lombok.Getter;

public class FragmentPackage
{
	/**
	 * The fragment to instantiate
	 */
	@Getter protected FragmentIntent fragmentIntent;

	/**
	 * The JSON string page data read from cache
	 */
	@Getter protected PageDescriptor pageDescriptor;

	public FragmentPackage()
	{
	}

	public FragmentPackage(FragmentIntent fragmentIntent, PageDescriptor pageDescriptor)
	{
		this.fragmentIntent = fragmentIntent;
		this.pageDescriptor = pageDescriptor;
	}
}
