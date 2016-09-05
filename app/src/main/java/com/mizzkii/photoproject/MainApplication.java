package com.mizzkii.photoproject;

import android.app.Application;

import com.mizzkii.photoproject.manager.Contextor;

/**
 * Created by MizzKii on 9/3/2016 AD.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
