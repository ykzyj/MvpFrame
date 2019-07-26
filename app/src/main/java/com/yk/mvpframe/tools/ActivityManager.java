package com.yk.mvpframe.tools;

import android.app.Activity;

import java.util.Stack;

/**
 * @FileName ActivityManager
 * @Author alan
 * @Date 2019/7/25 16:30
 * @Describe TODO
 * @Mark
 **/
public class ActivityManager {
    private static ActivityManager instance;
    private Stack<Activity> activityStack;

    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public void pushActivity(Activity actvity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(actvity);
    }

    public Activity getLastActivity() {
        return activityStack.lastElement();
    }

    public void popActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
                activity = null;
            }

        }
    }

    public void finishAllActivity() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = getLastActivity();
                if (activity == null){
                    break;
                }
                popActivity(activity);
            }
        }

    }
}