package com.example.mylego.ui.moc;

public class MocSingleItem {
    private int mImageResource;
    private String mMocName;
    private String mMocPartsNumber;

    public MocSingleItem(int imageResource, String mocName, String mocPartsNumber) {
        mImageResource = imageResource;
        mMocName = mocName;
        mMocPartsNumber = mocPartsNumber;

    }
    public int getImageResource() {
        return mImageResource;
    }
    public String getMocName() {
        return mMocName;
    }
    public String getmMocPartsNumber() {
        return mMocPartsNumber;
    }

}
