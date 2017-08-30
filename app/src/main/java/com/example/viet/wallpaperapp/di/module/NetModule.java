package com.example.viet.wallpaperapp.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.viet.wallpaperapp.common.Constants.BASE_URL;

/**
 * Created by viet on 29/08/2017.
 */
@Module
@Singleton
public class NetModule {
    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
