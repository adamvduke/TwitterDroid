package com.apprenaissance.test;

import android.test.ActivityInstrumentationTestCase2;
import com.apprenaissance.*;

public class TimelineActivityTest extends ActivityInstrumentationTestCase2<TimelineActivity> {

    public TimelineActivityTest() {
        super(TimelineActivity.class); 
    }

    public void testActivity() {
        TimelineActivity activity = getActivity();
        assertNotNull(activity);
    }
}

