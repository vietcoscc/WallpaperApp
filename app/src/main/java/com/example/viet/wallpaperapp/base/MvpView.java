package com.example.viet.wallpaperapp.base;

import android.app.ProgressDialog;

/**
 * Created by viet on 29/08/2017.
 */

public interface MvpView {
    void showProgress(ProgressDialog dialog);

    void hideProgress(ProgressDialog dialog);
}
