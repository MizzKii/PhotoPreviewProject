package com.mizzkii.photoproject.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mizzkii.photoproject.dao.PhotoItemCollectionDao;
import com.mizzkii.photoproject.dao.PhotoItemDao;
import com.mizzkii.photoproject.view.PhotoGridItem;
import com.mizzkii.photoproject.view.PhotoListItem;

/**
 * Created by MizzKii on 9/4/2016 AD.
 */
public class PhotoListAdapter extends BaseAdapter {

    private static PhotoListAdapter instance;

    public static PhotoListAdapter getInstance() {
        if (instance == null)
            instance = new PhotoListAdapter();
        return instance;
    }

    private PhotoItemCollectionDao dao;

    private PhotoListAdapter() {
    }

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
    }

    private boolean isViewList = true;

    public void setViewList(boolean viewList) {
        isViewList = viewList;
    }

    @Override
    public int getCount() {
        if (dao == null)
            return 0;
        if (dao.getData() == null)
            return 0;
        return dao.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return dao.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position % 2 == 0 ? 0 : 1;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (isViewList) {
            PhotoListItem item;
            if (convertView != null)
                item = (PhotoListItem) convertView;
            else
                item = new PhotoListItem(parent.getContext());

            PhotoItemDao dao = (PhotoItemDao) getItem(position);
            item.setNameText(dao.getCaption());
            item.setDescriptionText(dao.getUsername() + "\n" + dao.getCamera());
            item.setImageUrl(dao.getImageUrl());

            return item;
        } else {
            PhotoGridItem item;
            if (convertView != null)
                item = (PhotoGridItem) convertView;
            else
                item = new PhotoGridItem(parent.getContext());

            PhotoItemDao dao = (PhotoItemDao) getItem(position);
            item.setNameText(dao.getCaption());
            item.setDescriptionText(dao.getUsername());
            item.setImageUrl(dao.getImageUrl());

            return item;
        }
    }
}
