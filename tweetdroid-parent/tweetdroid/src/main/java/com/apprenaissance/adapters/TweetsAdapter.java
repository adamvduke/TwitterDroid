package com.apprenaissance.adapters;

import java.net.URI;
import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apprenaissance.R;
import com.apprenaissance.models.Tweet;
import com.apprenaissance.tasks.DownloadAvatarImageTask;

public class TweetsAdapter extends BaseAdapter {

	private List<Tweet> tweets;
	private LayoutInflater layoutInflater;

	public TweetsAdapter(Context context, List<Tweet> tweets) {
		this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.tweets = tweets;
	}

	public int getCount() {
		return tweets == null ? 0 : tweets.size();
	}

	public Object getItem(int position) {
		return tweets == null ? null : tweets.get(position);
	}

	public long getItemId(int position) {
		return -1;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.timeline_item, null);
        } 
        Tweet tweet = tweets.get(position);
        
        TextView textView = (TextView) convertView.findViewById(R.id.timeline_item_text_view); 
        textView.setText(tweet.getBody());
        
        ImageView imageView = (ImageView) convertView.findViewById(R.id.timeline_item_image_view);
        imageView.setTag(tweet.getImageUrl());
        new DownloadAvatarImageTask().execute(imageView);
        return convertView;
	}
}
