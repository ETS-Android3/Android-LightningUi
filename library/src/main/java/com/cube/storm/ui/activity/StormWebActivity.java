package com.cube.storm.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cube.storm.ui.R;

/**
 * Web browser to launch website from URI
 * <p/>
 * Can take either a single URI extra using the key {@link StormActivity#EXTRA_URI}
 *
 * @author Alan Le Fournis
 * @project LightningUi
 */
public class StormWebActivity extends AppCompatActivity implements OnClickListener
{
	public static final String EXTRA_FILE_NAME = "extra_file_name";
	public static final String EXTRA_TITLE = "extra_title";

	public View mWeb;
	public View mShare;
	public View mBack;
	public View mForward;
	public View mClose;
	public View mButtonContainer;

	private WebView webView;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(EXTRA_FILE_NAME))
		{
			String fileName = getIntent().getExtras().getString(EXTRA_FILE_NAME);

			if (getIntent().getExtras().containsKey(EXTRA_TITLE))
			{
				setTitle(getIntent().getExtras().getString(EXTRA_TITLE));
			}

			setContentView(R.layout.web_view);

			mButtonContainer = findViewById(R.id.button_container);
			mWeb = findViewById(R.id.icon_web);
			mBack = findViewById(R.id.icon_back);
			mForward = findViewById(R.id.icon_forward);
			mClose = findViewById(R.id.icon_close);
			mShare = findViewById(R.id.icon_share);
			webView = (WebView)findViewById(R.id.web_view);

			mWeb.setOnClickListener(this);
			mBack.setOnClickListener(this);
			mForward.setOnClickListener(this);
			mClose.setOnClickListener(this);
			mShare.setOnClickListener(this);

			WebSettings settings = webView.getSettings();
			settings.setJavaScriptEnabled(true);
			settings.setBuiltInZoomControls(true);
			settings.setLoadWithOverviewMode(true);
			settings.setUseWideViewPort(true);

			if (Build.VERSION.SDK_INT >= 11)
			{
				settings.setDisplayZoomControls(false);
			}

			final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress_bar);
			webView.setWebViewClient(new WebViewClient());
			webView.setWebChromeClient(new WebChromeClient()
			{
				@Override public void onProgressChanged(WebView view, int progress)
				{
					if (progress < 100 && progressBar.getVisibility() == View.GONE)
					{
						progressBar.setVisibility(View.VISIBLE);
					}

					progressBar.setProgress(progress);
					if (progress == 100)
					{
						progressBar.setVisibility(View.GONE);
					}

					super.onProgressChanged(view, progress);
				}
			});

			if (savedInstanceState != null)
			{
				webView.restoreState(savedInstanceState);
			}
			else
			{
				webView.loadUrl(fileName);
			}
		}
		else
		{
			Toast.makeText(this, "No url set", Toast.LENGTH_LONG).show();
			finish();
		}
	}

	@Override public void onClick(View v)
	{
		if (v == mWeb)
		{
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(webView.getUrl() == null ? getIntent().getExtras().getString(EXTRA_FILE_NAME) : webView.getUrl()));
			startActivity(i);
		}
		else if (v == mBack)
		{
			webView.goBack();
		}
		else if (v == mForward)
		{
			webView.goForward();
		}
		else if (v == mClose)
		{
			finish();
		}
		else if (v == mShare)
		{
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.putExtra(Intent.EXTRA_TEXT, webView.getUrl());
			shareIntent.setType("text/plain");
			startActivity(shareIntent);
		}
	}

	@Override public void onSaveInstanceState(Bundle savedInstanceState)
	{
		webView.saveState(savedInstanceState);
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
		webView.restoreState(savedInstanceState);
	}

	@Override public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuItem back = menu.add(0, 2, 0, "Back");
		MenuItem forward = menu.add(0, 3, 0, "Forward");
		MenuItem refresh = menu.add(0, 1, 0, "Refresh");
		MenuItem open = menu.add(0, 4, 0, "Open external");

		MenuItemCompat.setShowAsAction(back, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		MenuItemCompat.setShowAsAction(forward, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		MenuItemCompat.setShowAsAction(refresh, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		MenuItemCompat.setShowAsAction(open, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

		return super.onCreateOptionsMenu(menu);
	}

	@Override public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack())
		{
			webView.goBack();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == 4)
		{
			onClick(mWeb);
		}
		else if (item.getItemId() == 2)
		{
			onClick(mBack);
		}
		else if (item.getItemId() == 3)
		{
			webView.goForward();
		}
		else if (item.getItemId() == 1)
		{
			webView.reload();
		}

		return super.onOptionsItemSelected(item);
	}
}
