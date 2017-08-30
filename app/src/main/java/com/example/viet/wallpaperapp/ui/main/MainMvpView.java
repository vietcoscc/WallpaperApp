package com.example.viet.wallpaperapp.ui.main;

import com.example.viet.wallpaperapp.base.MvpView;
import com.example.viet.wallpaperapp.model.Category;

import java.util.ArrayList;

/**
 * Created by viet on 29/08/2017.
 */

public interface MainMvpView extends MvpView {
    void displayCategory(ArrayList<Category> arCategory);
}
