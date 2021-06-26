package com.example.mylego.ui.parts;

import android.net.Uri;

public class PartSingleItem {
    private int imageResource;
    private String partNumber;
    private String partName;
    private String imageUrl;
    private Uri thumbnailPath;

    public PartSingleItem() { }

    public int getImageResource() {
        return imageResource;
    }
    public String getPartNumber() {
        return partNumber;
    }
    public String getPartName() {
        return partName;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public Uri getThumbnailPath() {
        return thumbnailPath;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }
    public void setPartName(String partName) {
        this.partName = partName;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setThumbnailPath(Uri thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}
