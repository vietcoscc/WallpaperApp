package com.example.viet.wallpaperapp.di.component;

import com.example.viet.wallpaperapp.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by viet on 29/08/2017.
 */
@Component(modules = NetModule.class)
@Singleton
public interface NetComponent {
    Retrofit retrofit();
}
