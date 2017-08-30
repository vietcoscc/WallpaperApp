package com.example.viet.wallpaperapp;

import android.app.Application;

import com.example.viet.wallpaperapp.di.component.ActivityComponent;
import com.example.viet.wallpaperapp.di.component.DaggerActivityComponent;
import com.example.viet.wallpaperapp.di.component.DaggerNetComponent;
import com.example.viet.wallpaperapp.di.component.NetComponent;
import com.example.viet.wallpaperapp.di.module.ActivityModule;
import com.example.viet.wallpaperapp.di.module.NetModule;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;

/**
 * Created by viet on 29/08/2017.
 */

public class MyApplication extends Application {
    private ActivityComponent mActivityComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
        initFresco();
    }

    private void initComponent() {
        NetComponent netComponent = DaggerNetComponent
                .builder()
                .netModule(new NetModule())
                .build();
        mActivityComponent = DaggerActivityComponent
                .builder()
                .activityModule(new ActivityModule())
                .netComponent(netComponent)
                .build();
    }

    private void initFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .setResizeAndRotateEnabledForNetwork(true)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }

    public ActivityComponent getmActivityComponent() {
        return mActivityComponent;
    }
}
