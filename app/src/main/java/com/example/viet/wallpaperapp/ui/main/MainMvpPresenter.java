package com.example.viet.wallpaperapp.ui.main;

import android.app.ProgressDialog;

import com.example.viet.wallpaperapp.base.MvpPresenter;

/**
 * Created by viet on 29/08/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void getCategory(ProgressDialog dialog);

}
