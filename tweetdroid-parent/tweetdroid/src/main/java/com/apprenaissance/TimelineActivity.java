package com.apprenaissance;

import java.io.File;

import android.app.ListActivity;
import android.net.http.HttpResponseCache;
import android.os.Bundle;

import com.apprenaissance.tasks.DownloadTimelineTask;

public class TimelineActivity extends ListActivity {

	/**
	 * Called when the activity is first created.
	 *
	 * @param savedInstanceState
	 *            If the activity is being re-initialized after previously being
	 *            shut down then this Bundle contains the data it most recently
	 *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
	 *            is null.</b>
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		enableHttpResponseCache();
		new DownloadTimelineTask<TimelineActivity>(this).execute("https://api.twitter.com/1/statuses/public_timeline.json?include_entities=true");
	}

	private void enableHttpResponseCache() {
		try {
			long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
			File httpCacheDir = new File(getCacheDir(), "http");
			HttpResponseCache.install(httpCacheDir, httpCacheSize);
		} catch (Exception httpResponseCacheNotAvailable) {
			httpResponseCacheNotAvailable.printStackTrace();
		}
	}
}
