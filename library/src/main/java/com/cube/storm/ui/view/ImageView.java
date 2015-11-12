package com.cube.storm.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.model.property.AnimationImageProperty;
import com.cube.storm.ui.model.property.ImageProperty;
import com.cube.storm.ui.model.property.SpotlightImageProperty;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

/**
 * Augmented image view which uses the UniveralImageLoader library to display images and handles Storm animations.
 * <p/>
 *
 * @author Tim Matthews
 * @Project LightningUi
 */
public class ImageView extends android.widget.ImageView
{
	/**
	 * Listener alerted whenever the animation frame changes.
	 */
	public interface OnAnimationFrameChangeListener
	{
		void onAnimationFrameChange(ImageView imageView, int frameIndex, AnimationImageProperty frame);
	}

	/**
	 * Handler used to control animations. Null until an animation is explicitly
	 */
	private Handler animator = null;

	/**
	 * Runnable used to update the curent frame. Null until an animation is explicitly
	 */
	private Runnable displayNextFrame = null;

	public ImageView(Context context)
	{
		super(context);
	}

	public ImageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public ImageView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	public ImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	/**
	 * Alternately display each of a sequence of Storm animation frames.
	 *
	 * @param frames
	 * 		If null, the current image is cleared and all pending animation tasks are cancelled.
	 */
	public void populate(@Nullable final List<? extends AnimationImageProperty> frames)
	{
		populate(frames, null);
	}

	/**
	 * Alternately display each of a sequence of Storm animation frames, alerting an optional listener whenever the
	 * frame changes.
	 *
	 * @param frames
	 * 		If null, the current image is cleared and all pending animation tasks are cancelled.
	 * @param listener
	 */
	public void populate(@Nullable final List<? extends AnimationImageProperty> frames, @Nullable final OnAnimationFrameChangeListener listener)
	{
		// Cancel all current loading tasks
		populate((ImageProperty)null);

		if (frames != null)
		{
			if (animator == null)
			{
				animator = new Handler();
			}

			displayNextFrame = new Runnable()
			{
				private int frameIndex = 0;

				@Override public void run()
				{
					AnimationImageProperty frame = frames.get(frameIndex % frames.size());
					populateFrame(frame, null);

					if (listener != null)
					{
						listener.onAnimationFrameChange(ImageView.this, frameIndex % frames.size(), frame);
					}

					++frameIndex;

					if (frames.size() > 1)
					{
						animator.postDelayed(this, frame.getDelay());
					}
				}
			};
		}
	}

	@Override protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();

		if (animator != null)
		{
			animator.removeCallbacks(displayNextFrame);
		}
	}

	@Override protected void onAttachedToWindow()
	{
		super.onAttachedToWindow();

		if (animator != null)
		{
			animator.post(displayNextFrame);
		}
	}

	/**
	 * Alternately display each of a sequence of Storm spotlight frames, updating a text view to display the spotlight
	 * text whenever it changes.
	 *
	 * @param frames
	 * 		If null, the current image is cleared and all pending animation tasks are cancelled.
	 * @param textView
	 * 		{@link TextView#populate(com.cube.storm.ui.model.property.TextProperty, com.cube.storm.ui.model.property.LinkProperty)}
	 * 		will be called with the relevant details each time the spotlight changes.
	 */
	public void populate(@Nullable final List<? extends SpotlightImageProperty> frames, @NonNull TextView textView)
	{
		populate(frames, textView, null);
	}

	/**
	 * Alternately display each of a sequence of Storm spotlight frames, updating a text view and alerting an optional
	 * listener whenever the spotlight changes.
	 *
	 * @param frames
	 * 		If null, the current image is cleared and all pending animation tasks are cancelled.
	 * @param textView
	 * 		{@link TextView#populate(com.cube.storm.ui.model.property.TextProperty, com.cube.storm.ui.model.property.LinkProperty)}
	 * 		will be called with the relevant details each time the spotlight changes.
	 * @param listener
	 */
	public void populate(@Nullable final List<? extends SpotlightImageProperty> frames, @NonNull final TextView textView, @Nullable final OnAnimationFrameChangeListener listener)
	{
		populate(frames, new OnAnimationFrameChangeListener()
		{
			@Override public void onAnimationFrameChange(ImageView imageView, int frameIndex, AnimationImageProperty frame)
			{
				SpotlightImageProperty spotlightFrame = (SpotlightImageProperty)frame;
				textView.populate(spotlightFrame.getText(), spotlightFrame.getLink());

				if (listener != null)
				{
					listener.onAnimationFrameChange(imageView, frameIndex, frame);
				}
			}
		});
	}

	/**
	 * Load and display the specified image asynchronously.
	 *
	 * @param image
	 * 		If null, the current image is cleared and all pending animation tasks are cancelled.
	 */
	public void populate(@Nullable final ImageProperty image)
	{
		populate(image, null);
	}

	/**
	 * Load and display the specified image asynchronously, with an optional progress bar visible until the image is
	 * loaded.
	 *
	 * @param image
	 * 		If null, the current image is cleared and all pending animation tasks are cancelled.
	 * @param progress
	 */
	public void populate(@Nullable final ImageProperty image, @Nullable final ProgressBar progress)
	{
		// The user is explicitly setting a static image so cancel the animation task
		if (animator != null && displayNextFrame != null)
		{
			animator.removeCallbacks(displayNextFrame);
		}

		populateFrame(image, progress);
	}

	/**
	 * Used internally by all other image population tasks.
	 *
	 * @param image
	 * @param progress
	 */
	private void populateFrame(@Nullable final ImageProperty image, @Nullable final ProgressBar progress)
	{
		UiSettings.getInstance().getImageLoader().cancelDisplayTask(this);

		if (image != null && (!TextUtils.isEmpty(image.getSrc()) || !TextUtils.isEmpty(image.getFallbackSrc())))
		{
			//setVisibility(View.INVISIBLE);
			UiSettings.getInstance()
				.getImageLoader()
				.displayImage(TextUtils.isEmpty(image.getSrc()) ? image.getFallbackSrc() : image.getSrc(), this, new SimpleImageLoadingListener()
				{
					@Override public void onLoadingStarted(String imageUri, View view)
					{
						if (animator == null)
						{
							setVisibility(View.INVISIBLE);
						}

						if (progress != null)
						{
							progress.setVisibility(View.VISIBLE);
						}
					}

					@Override public void onLoadingFailed(String imageUri, View view, FailReason failReason)
					{
						if (!imageUri.equalsIgnoreCase(image.getFallbackSrc()))
						{
							UiSettings.getInstance().getImageLoader().displayImage(image.getFallbackSrc(), ImageView.this, this);
							return;
						}

						setVisibility(View.GONE);
						if (progress != null)
						{
							progress.setVisibility(View.GONE);
						}
					}

					@Override public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
					{
						setVisibility(View.VISIBLE);
						if (progress != null)
						{
							progress.setVisibility(View.GONE);
						}
					}
				});
		}
		else
		{
			setImageBitmap(null);
			setVisibility(View.GONE);

			if (progress != null)
			{
				progress.setVisibility(View.GONE);
			}
		}
	}
}
