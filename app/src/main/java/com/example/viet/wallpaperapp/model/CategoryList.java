package com.example.viet.wallpaperapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by viet on 30/08/2017.
 */

public class CategoryList {
    @SerializedName("HD_WALLPAPER")
    @Expose
    private ArrayList<Category> arrCategory;

    public CategoryList(ArrayList<Category> arrCategory) {
        this.arrCategory = arrCategory;
    }

    public ArrayList<Category> getArrCategory() {
        return arrCategory;
    }

    public void setArrCategory(ArrayList<Category> arrCategory) {
        this.arrCategory = arrCategory;
    }
}
