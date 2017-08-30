package com.example.viet.wallpaperapp.di.module;

import com.example.viet.wallpaperapp.di.PerActivity;
import com.example.viet.wallpaperapp.ui.main.MainMvpPresenter;
import com.example.viet.wallpaperapp.ui.main.MainMvpView;
import com.example.viet.wallpaperapp.ui.main.MainPresenter;
import com.example.viet.wallpaperapp.ui.main.item.ItemMvpPresenter;
import com.example.viet.wallpaperapp.ui.main.item.ItemMvpView;
import com.example.viet.wallpaperapp.ui.main.item.ItemPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by viet on 29/08/2017.
 */
@Module
public class ActivityModule {
    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ItemMvpPresenter<ItemMvpView> provideItemPresenter(ItemPresenter<ItemMvpView> presenter) {
        return presenter;
    }
}
