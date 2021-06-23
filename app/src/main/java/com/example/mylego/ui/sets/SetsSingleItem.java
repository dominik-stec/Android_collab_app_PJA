package com.example.mylego.ui.sets;

import android.widget.ImageView;

public class SetsSingleItem {

    private String setNum;
    private String setName;
    private String setYear;
    private String setNumParts;
    private String imageUrl;
    private int imageResource;

    public SetsSingleItem() { }

    public SetsSingleItem(String setNum, String setName, String setYear, String setNumParts, String imageUrl) {
        this.setNum = setNum;
        this.setName = setName;
        this.setYear = setYear;
        this.setNumParts = setNumParts;
        this.imageUrl = imageUrl;
    }

    public SetsSingleItem(String setNum, String setName, String setYear, String setNumParts, int imageResource) {
        this.setNum = setNum;
        this.setName = setName;
        this.setYear = setYear;
        this.setNumParts = setNumParts;
        this.imageResource = imageResource;
    }
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
}
