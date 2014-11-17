package com.cube.storm.ui.lib.helper;

import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.response.StringResponseHandler;

/**
 * // TODO: Add class description
 *
 * @author Alan Le Fournis
 * @project Storm
 */
public class YouTubeHelper
{
	public interface Callback
	{
		public void onStreamingUrlFetched(String streamingUrl);
		public void onFailed(String failMessage);
	}

	public static void getStreamingUrl(String youtubeUrl, final Callback callback)
	{
		Uri uri = Uri.parse(youtubeUrl);
		final String videoId;

		if (uri.getHost().endsWith("youtube.com"))
		{
			videoId = uri.getQueryParameter("v");
		}
		else if (uri.getHost().contains("yout"))
		{
			videoId = uri.getPathSegments().get(0);
		}
		else
		{
			// fail
			return;
		}

		// download the video details
		AsyncHttpClient client = new AsyncHttpClient("http://www.youtube.com/");
		client.get("get_video_info?video_id=" + videoId, new StringResponseHandler()
		{
			private String streamingUrl;

			@Override public void onFinish(boolean failed)
			{
				if (failed || TextUtils.isEmpty(streamingUrl))
				{
					callback.onFailed(getContent());
				}
				else
				{
					callback.onStreamingUrlFetched(streamingUrl);
				}
			}

			@Override public void onSuccess()
			{
				try
				{
					String response = getContent();
					if (response != null)
					{
						String[] parts = response.split("&");
						String streamMap = "";

						for (String part : parts)
						{
							if (part.contains("url_encoded_fmt_stream_map"))
							{
								streamMap = part;
								break;
							}
						}

						boolean allowWebm = VERSION.SDK_INT >= 14;
						String disallow = "video/x-flv";

						streamMap = streamMap.replace("url_encoded_fmt_stream_map=", "");
						streamMap = Uri.decode(streamMap);
						String[] streamParts = streamMap.split(",");

						String url = null;

						for (String streamPart : streamParts)
						{
							String[] streamPartSections = streamPart.split("&");
							ArrayMap<String, String> map = new ArrayMap<String, String>();

							for (String streamPartSection : streamPartSections)
							{
								String[] mapParts = streamPartSection.split("=");
								map.put(mapParts[0], mapParts[1]);
							}

							if (Uri.decode(map.get("type")).contains(disallow) || (!allowWebm && Uri.decode(map.get("type")).contains("video/webm")))
							{
								continue;
							}

							if (map.get("quality").equals("medium"))
							{
								url = Uri.decode(map.get("url")) + "&signature=" + map.get("sig");
								break;
							}
							else if (map.get("quality").equals("small"))
							{
								url = Uri.decode(map.get("url")) + "&signature=" + map.get("sig");
								break;
							}
						}

						streamingUrl = url;
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
