package com.example.viet.wallpaperapp.ui.main;

import android.app.ProgressDialog;

import com.example.viet.wallpaperapp.api.WallpaperApi;
import com.example.viet.wallpaperapp.base.BasePresenter;
import com.example.viet.wallpaperapp.model.CategoryList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by viet on 29/08/2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {
    private static final String TAG = "MainPresenter";
    private Retrofit mRetrofit;

    @Inject
    public MainPresenter(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    @Override
    public void getCategory(final ProgressDialog dialog) {
        getmMvpView().showProgress(dialog);
        Call<CategoryList> call = mRetrofit.create(WallpaperApi.class).getCategory();
        call.enqueue(new Callback<CategoryList>() {
            @Override
            public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {
                CategoryList categoryList = response.body();
                getmMvpView().hideProgress(dialog);
                getmMvpView().displayCategory(categoryList.getArrCategory());
            }

            @Override
            public void onFailure(Call<CategoryList> call, Throwable t) {
                getmMvpView().hideProgress(dialog);
            }
        });
    }


}
