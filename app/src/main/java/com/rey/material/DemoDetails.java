package com.rey.material;

import android.app.Activity;

public class DemoDetails {
    private final String title;
    private final String description;
    private final Class<? extends android.app.Activity> activityClass;

    public DemoDetails(String title, String description,
                       Class<? extends android.app.Activity> activityClass) {
        this.title = title;
        this.description = description;
        this.activityClass = activityClass;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Class<? extends Activity> getActivityClass() {
        return activityClass;
    }
}