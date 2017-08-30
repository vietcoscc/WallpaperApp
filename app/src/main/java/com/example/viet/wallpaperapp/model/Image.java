package com.example.viet.wallpaperapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by viet on 29/08/2017.
 */

public class Image {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("cat_id")
    @Expose
    String catId;
    @SerializedName("wallpaper_image")
    @Expose
    String wallpaperImage;
    @SerializedName("wallpaper_image_thumb")
    @Expose
    String wallpagerImageThumb;
    @SerializedName("total_views")
    @Expose
    String totalViews;
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

    public Image(String id,
                 String catId,
                 String wallpaperImage,
                 String wallpagerImageThumb,
                 String totalViews,
                 String cId,
                 String categoryName,
                 String categoryImage,
                 String categoryImageThumb,
                 String totalWallpager) {
        this.id = id;
        this.catId = catId;
        this.wallpaperImage = wallpaperImage;
        this.wallpagerImageThumb = wallpagerImageThumb;
        this.totalViews = totalViews;
        this.cId = cId;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.categoryImageThumb = categoryImageThumb;
        this.totalWallpager = totalWallpager;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getWallpaperImage() {
        return wallpaperImage;
    }

    public void setWallpaperImage(String wallpaperImage) {
        this.wallpaperImage = wallpaperImage;
    }

    public String getWallpagerImageThumb() {
        return wallpagerImageThumb;
    }

    public void setWallpagerImageThumb(String wallpagerImageThumb) {
        this.wallpagerImageThumb = wallpagerImageThumb;
    }

    public String getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(String totalViews) {
        this.totalViews = totalViews;
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
