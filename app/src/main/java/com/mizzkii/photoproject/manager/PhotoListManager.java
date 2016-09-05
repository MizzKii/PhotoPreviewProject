package com.mizzkii.photoproject.manager;

import android.content.Context;

import com.mizzkii.photoproject.dao.PhotoItemCollectionDao;

/**
 * Created by MizzKii on 9/5/2016 AD.
 */
public class PhotoListManager {

    private static PhotoListManager instance;

    public static PhotoListManager getInstance() {
        if(instance == null) {
            instance = new PhotoListManager();
        }
        return instance;
    }

    private Context mContext;
    private PhotoItemCollectionDao dao;

    private PhotoListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public PhotoItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
    }
}
