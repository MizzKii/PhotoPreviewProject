package com.mizzkii.photoproject.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mizzkii.photoproject.dao.PhotoItemDao;
import com.mizzkii.photoproject.manager.PhotoListManager;
import com.mizzkii.photoproject.view.PhotoListItem;

/**
 * Created by MizzKii on 9/4/2016 AD.
 */
public class PhotoListAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        if(PhotoListManager.getInstance().getDao() == null)
            return 0;
        if(PhotoListManager.getInstance().getDao().getData() == null)
            return 0;
        return PhotoListManager.getInstance().getDao().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return PhotoListManager.getInstance().getDao().getData().get(position);
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
//        if(getItemViewType(position) == 0) {
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
//        } else {
//            TextView item;
//            if (convertView != null)
//                item = (TextView) convertView;
//            else
//            item = new TextView(parent.getContext());
//            return item;
//        }
    }
}
