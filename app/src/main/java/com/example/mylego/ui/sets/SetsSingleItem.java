package com.example.mylego.ui.sets;

public class SetsSingleItem {

    private int mImageResource;
    private String mSetNumAndSetName;
    private String mSetYear;
    private String mSetNumParts;

    public SetsSingleItem(int imageResource, String setNumAndSetName, String setYear, String setNumParts) {
        mImageResource = imageResource;
        mSetNumAndSetName = setNumAndSetName;
        mSetYear = setYear;
        mSetNumParts = setNumParts;
    }
    public int getImageResource() {
        return mImageResource;
    }
    public String getSetNumAndSetName() {
        return mSetNumAndSetName;
    }
    public String getSetYear() {
        return mSetYear;
    }
    public String getSetNumParts() {
        return  mSetNumParts;
    }
}
