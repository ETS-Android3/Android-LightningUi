package com.cube.storm.ui.model.descriptor;

import android.os.Parcel;
import android.os.Parcelable;

import com.cube.storm.ui.model.TabBarItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true) @Data @EqualsAndHashCode(callSuper=false)
public class TabbedPageDescriptor extends PageDescriptor implements Parcelable
{
	public static String CLASS_NAME = "TabbedPageDescriptor";

	{ this.className = CLASS_NAME; }

	protected TabBarItem tabBarItem;
	protected int tabIndex = 0;

	private TabbedPageDescriptor(Parcel in)
	{
		tabIndex = in.readInt();
	}

	public static final Creator<TabbedPageDescriptor> CREATOR = new Creator<TabbedPageDescriptor>()
	{
		@Override
		public TabbedPageDescriptor createFromParcel(Parcel in)
		{
			return new TabbedPageDescriptor(in);
		}

		@Override
		public TabbedPageDescriptor[] newArray(int size)
		{
			return new TabbedPageDescriptor[size];
		}
	};

	@Override public int describeContents()
	{
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeInt(tabIndex);
	}
}
