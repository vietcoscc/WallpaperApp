package com.example.viet.wallpaperapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by viet on 30/08/2017.
 */

public class ImageList {
    @SerializedName("HD_WALLPAPER")
    @Expose
    private ArrayList<Image> arrImage;

    public ImageList(ArrayList<Image> arrImage) {
        this.arrImage = arrImage;
    }

    public ArrayList<Image> getArrImage() {
        return arrImage;
    }

    public void setArrImage(ArrayList<Image> arrImage) {
        this.arrImage = arrImage;
    }
}
