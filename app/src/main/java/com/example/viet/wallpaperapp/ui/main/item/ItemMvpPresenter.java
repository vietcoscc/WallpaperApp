package com.example.viet.wallpaperapp.ui.main.item;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;

import com.example.viet.wallpaperapp.base.MvpPresenter;
import com.example.viet.wallpaperapp.model.Image;

import java.util.ArrayList;

/**
 * Created by viet on 30/08/2017.
 */

public interface ItemMvpPresenter<V extends ItemMvpView> extends MvpPresenter<V> {
    void getLastest(ProgressDialog dialog);

    void getCategoryItem(ProgressDialog dialog, String catId);

    void createImageViewer(Context context,View view, int position,  ArrayList<Image> arrImage);

    void createPopupMenu(Context context,View view, int position,  ArrayList<Image> arrImage);
}
