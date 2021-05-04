package com.example.mylego.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BricksSingleSet implements Serializable {

    @SerializedName("set_num")
    @Expose
    String setNum;

    @SerializedName("name")
    @Expose
    String name;

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
    String setImgUrl;

    @SerializedName("set_url")
    @Expose
    String setUrl;

    @SerializedName("last_modified_dt")
    @Expose
    String lastModifiedDt;

    public String getSetNum() {
        return setNum;
    }

    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public String getSetImgUrl() {
        return setImgUrl;
    }

    public void setSetImgUrl(String setImgUrl) {
        this.setImgUrl = setImgUrl;
    }

    public String getSetUrl() {
        return setUrl;
    }

    public void setSetUrl(String setUrl) {
        this.setUrl = setUrl;
    }

    public String getLastModifiedDt() {
        return lastModifiedDt;
    }

    public void setLastModifiedDt(String lastModifiedDt) {
        this.lastModifiedDt = lastModifiedDt;
    }
}
