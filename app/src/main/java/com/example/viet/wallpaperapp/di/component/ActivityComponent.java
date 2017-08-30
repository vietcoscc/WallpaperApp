package com.example.viet.wallpaperapp.di.component;

import com.example.viet.wallpaperapp.di.PerActivity;
import com.example.viet.wallpaperapp.di.module.ActivityModule;
import com.example.viet.wallpaperapp.ui.main.MainActivity;
import com.example.viet.wallpaperapp.ui.main.item.ItemActivity;

import dagger.Component;

/**
 * Created by viet on 29/08/2017.
 */
@Component(modules = ActivityModule.class, dependencies = NetComponent.class)
@PerActivity
public interface ActivityComponent {
    void inject(MainActivity activity);

    void inject(ItemActivity activity);
}
