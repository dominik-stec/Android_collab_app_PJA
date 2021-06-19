package com.example.mylego.ui.parts;

public class PartSingleItem {
    private int mImageResource;
    private String mPartNumber;
    private String mPartName;

    public PartSingleItem(int imageResource, String partNumber, String partName) {
        mImageResource = imageResource;
        mPartNumber = partNumber;
        mPartName = partName;
    }
    public int getImageResource() {
        return mImageResource;
    }
    public String getPartNumber() {
        return mPartNumber;
    }
    public String getPartName() {
        return mPartName;
    }
}
