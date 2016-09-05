package com.mizzkii.photoproject.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MizzKii on 9/5/2016 AD.
 */
public class PhotoItemCollectionDao {

    @SerializedName("success")  private boolean success;
    @SerializedName("data")     private List<PhotoItemDao> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<PhotoItemDao> getData() {
        return data;
    }

    public void setData(List<PhotoItemDao> data) {
        this.data = data;
    }
}
