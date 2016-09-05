package com.mizzkii.photoproject.manager.http;

import com.mizzkii.photoproject.dao.PhotoItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by MizzKii on 9/5/2016 AD.
 */
public interface ApiService {

    @POST("list")
    Call<PhotoItemCollectionDao> loadPhotoList();

}
