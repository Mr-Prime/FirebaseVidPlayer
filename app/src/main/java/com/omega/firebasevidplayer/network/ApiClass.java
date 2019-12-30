package com.omega.firebasevidplayer.network;

import com.omega.firebasevidplayer.model.response.HomeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mr.Prime on 29-12-2019.
 */
public interface  ApiClass {

    /**
     * Retrieve a list of items
     */
    @GET("media.json/")
    Call<List<HomeResponse>> getVideos();
}
