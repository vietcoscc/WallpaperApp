package com.example.viet.wallpaperapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by viet on 29/08/2017.
 */

public class Category {
    @SerializedName("cid")
    @Expose
    String cId;
    @SerializedName("category_name")
    @Expose
    String categoryName;
    @SerializedName("category_image")
    @Expose
    String categoryImage;
    @SerializedName("category_image_thumb")
    @Expose
    String categoryImageThumb;
    @SerializedName("total_wallpaper")
    @Expose
    String totalWallpager;

    public Category(String cId, String categoryName, String categoryImage, String categoryImageThumb, String totalWallpager) {
        this.cId = cId;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.categoryImageThumb = categoryImageThumb;
        this.totalWallpager = totalWallpager;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryImageThumb() {
        return categoryImageThumb;
    }

    public void setCategoryImageThumb(String categoryImageThumb) {
        this.categoryImageThumb = categoryImageThumb;
    }

    public String getTotalWallpager() {
        return totalWallpager;
    }

    public void setTotalWallpager(String totalWallpager) {
        this.totalWallpager = totalWallpager;
    }
}
