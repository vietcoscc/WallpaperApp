package com.example.viet.wallpaperapp.base;

/**
 * Created by viet on 29/08/2017.
 */

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);

    void onDetach();
}
