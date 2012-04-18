package com.apprenaissance;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;

import com.apprenaissance.adapters.TweetsAdapter;
import com.apprenaissance.models.Tweet;

public class TimelineActivity extends ListActivity {

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down then this Bundle contains the data it most
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       List<Tweet> tweets = new ArrayList<Tweet>();
       tweets.add(new Tweet("Poopin", "http://a0.twimg.com/profile_images/1252900035/Photo_on_2011-02-21_at_18.39__2.jpg"));
       tweets.add(new Tweet("Poopin more...", "http://a0.twimg.com/profile_images/1252900035/Photo_on_2011-02-21_at_18.39__2.jpg"));
       
       setListAdapter(new TweetsAdapter(this, tweets));
    }
}

