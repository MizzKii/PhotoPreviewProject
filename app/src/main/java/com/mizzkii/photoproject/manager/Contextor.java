package com.mizzkii.photoproject.manager;

import android.content.Context;

/**
 * Created by MizzKii on 9/3/2016 AD.
 */
public class Contextor {

    private static Contextor instance;
    public static Contextor getInstance() {
        if(instance == null) {
            instance = new Contextor();
        }
        return instance;
    }

    private Context mContext;
    public void init(Context mContext) {
        this.mContext = mContext;
    }

    public Context getContext() {
        return mContext;
    }
}
