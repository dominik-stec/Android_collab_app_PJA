package com.example.mylego.ui.sets;

import android.net.Uri;

public class SetsSingleItem {

    private String setNum;
    private String setName;
    private String setYear;
    private String setNumParts;
    private String imageUrl;
    private int imageResource;
    private Uri thumbnailPath;

    public SetsSingleItem() { }

    public String getSetNum() {
        return setNum;
    }
    public String getSetName() {
        return setName;
    }
    public String getSetYear() {
        return setYear;
    }
    public String getSetNumParts() {
        return setNumParts;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public int getImageResource() {
        return imageResource;
    }
    public Uri getThumbnailPath() {
        return thumbnailPath;
    }

    public void setSetNum(String num) {
        this.setNum = num;
    }
    public void setSetName(String name) {
        this.setName = name;
    }
    public void setSetYear(String year) {
        this.setYear = year;
    }
    public void setSetNumParts(String numParts) {
        this.setNumParts = numParts;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
    public void setThumbnailPath(Uri thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}
