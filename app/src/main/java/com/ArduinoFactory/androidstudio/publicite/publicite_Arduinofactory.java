package com.ArduinoFactory.androidstudio.publicite;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;

public class publicite_Arduinofactory extends Application implements Application.ActivityLifecycleCallbacks {

    private AppOpenAdManager appOpenAdManager;
    private Activity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this);
        registerActivityLifecycleCallbacks(this);
        appOpenAdManager = new AppOpenAdManager(this);
    }

    @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}
    @Override public void onActivityStarted(Activity activity) {
        currentActivity = activity;
        appOpenAdManager.showAdIfAvailable(activity);
    }
    @Override public void onActivityResumed(Activity activity) {
        currentActivity = activity;
    }
    @Override public void onActivityPaused(Activity activity) {}
    @Override public void onActivityStopped(Activity activity) {}
    @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
    @Override public void onActivityDestroyed(Activity activity) {}
}
