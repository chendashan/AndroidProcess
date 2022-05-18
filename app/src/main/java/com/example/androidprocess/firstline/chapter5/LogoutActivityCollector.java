package com.example.androidprocess.firstline.chapter5;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class LogoutActivityCollector {
    private static List<Activity> mActivityList = new ArrayList<>();

    private static void addActivity(Activity activity) {
        mActivityList.add(activity);
    }

    private static void removeActivity(Activity activity) {
        mActivityList.remove(activity);
    }

    private static void finishAll() {
        for (Activity activity : mActivityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        mActivityList.clear();
    }
}
