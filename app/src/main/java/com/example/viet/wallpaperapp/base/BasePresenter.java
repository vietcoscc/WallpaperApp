package com.example.viet.wallpaperapp.base;

/**
 * Created by viet on 29/08/2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private V mMvpView;

    @Override
    public void onAttach(MvpView mvpView) {
        this.mMvpView = (V) mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }

    public V getmMvpView() {
        return mMvpView;
    }
}
