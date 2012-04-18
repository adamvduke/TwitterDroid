package com.apprenaissance.tasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListActivity;
import android.os.AsyncTask;

import com.apprenaissance.adapters.TweetsAdapter;
import com.apprenaissance.models.Tweet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DownloadTimelineTask<TContext extends ListActivity> extends AsyncTask<String, Void, List<Tweet>> {

	private TContext context;
	
	public DownloadTimelineTask(TContext context) {
		this.context = context;
	}
	
	@Override
	protected List<Tweet> doInBackground(String... timelineUrls) {
		String url = timelineUrls[0];
		return downloadTimeline(url);
	}

	@SuppressWarnings("unchecked")
	private List<Tweet> downloadTimeline(String url) {
		HttpGet request = new HttpGet(url);
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(request);
			InputStream content = response.getEntity().getContent();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
			Gson parser = new GsonBuilder().create();
			ArrayList<Map<String,Object>> responseObjects = parser.fromJson(bufferedReader, ArrayList.class);
			ArrayList<Tweet> tweets = new ArrayList<Tweet>();
			for(Map<String, Object> responseObject : responseObjects) {
				String body = (String) responseObject.get("text");
				Map<String, Object> user =  (Map<String, Object>) responseObject.get("user");
				String imageUrl = (String) user.get("profile_image_url");
				Tweet tweet = new Tweet(body, imageUrl);
				tweets.add(tweet);
			}
			return tweets;
			
		} catch (Exception exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	protected void onPostExecute(List<Tweet> result) {
		TweetsAdapter adapter = new TweetsAdapter(context, result);
		context.setListAdapter(adapter);
	}
}
