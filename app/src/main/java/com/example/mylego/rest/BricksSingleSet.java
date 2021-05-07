package com.example.mylego.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BricksSingleSet implements Serializable {

    @SerializedName("set_num")
    @Expose
    java.lang.String setNum;

    @SerializedName("name")
    @Expose
    java.lang.String name;

    @SerializedName("year")
    @Expose
    int year;

    @SerializedName("theme_id")
    @Expose
    int themeId;

    @SerializedName("num_parts")
    @Expose
    int numParts;

    @SerializedName("set_img_url")
    @Expose
    java.lang.String setImgUrl;

    @SerializedName("set_url")
    @Expose
    java.lang.String setUrl;

    @SerializedName("last_modified_dt")
    @Expose
    java.lang.String lastModifiedDt;

    public java.lang.String getSetNum() {
        return setNum;
    }

    public void setSetNum(java.lang.String setNum) {
        this.setNum = setNum;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getNumParts() {
        return numParts;
    }

    public void setNumParts(int numParts) {
        this.numParts = numParts;
    }

    public java.lang.String getSetImgUrl() {
        return setImgUrl;
    }

    public void setSetImgUrl(java.lang.String setImgUrl) {
        this.setImgUrl = setImgUrl;
    }

    public java.lang.String getSetUrl() {
        return setUrl;
    }

    public void setSetUrl(java.lang.String setUrl) {
        this.setUrl = setUrl;
    }

    public java.lang.String getLastModifiedDt() {
        return lastModifiedDt;
    }

    public void setLastModifiedDt(java.lang.String lastModifiedDt) {
        this.lastModifiedDt = lastModifiedDt;
    }
}
