package com.example.mylego.ui.sets;

public class SetsSingleItem {

    private String imageResource;
    private String setNum;
    private String setName;
    private String setYear;
    private String setNumParts;

    public SetsSingleItem() { }

    public SetsSingleItem(String setNum, String setName, String setYear, String setNumParts, String imageResource) {
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
    public String getImageResource() {
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
    public void setImageResource(String imageUrl) {
        this.imageResource = imageUrl;
    }
}
