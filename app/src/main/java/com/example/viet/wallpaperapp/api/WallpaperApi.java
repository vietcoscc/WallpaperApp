package com.example.viet.wallpaperapp.api;

import com.example.viet.wallpaperapp.model.CategoryList;
import com.example.viet.wallpaperapp.model.ImageList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by viet on 29/08/2017.
 */

public interface WallpaperApi {
    @GET("api.php?latest")
    Call<ImageList> getLastest();

    @GET("api.php?cat_list")
    Call<CategoryList> getCategory();

    @GET("api.php")
    Call<ImageList> getCategoryItem(@Query("cat_id") String catId);
}
