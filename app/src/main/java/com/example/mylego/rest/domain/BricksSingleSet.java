package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BricksSingleSet implements Serializable {
    @SerializedName("_id")
    @Expose
    long _id;

    @SerializedName("set_num")
    @Expose
    String set_number;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("year")
    @Expose
    int year;

    @SerializedName("theme_id")
    @Expose
    int theme_id;

    @SerializedName("num_parts")
    @Expose
    int number_of_parts;

    @SerializedName("set_img_url")
    @Expose
    String image_url;

    @SerializedName("set_url")
    @Expose
    String set_url;

    @SerializedName("last_modified_dt")
    @Expose
    String modification_date;

    public BricksSingleSet() { }

    public BricksSingleSet(long id, String setNum, String name, int year, int themeId, int numParts, String setImgUrl, String setUrl, String lastModifiedDt) {
        this._id = id;
        this.set_number = setNum;
        this.name = name;
        this.year = year;
        this.theme_id = themeId;
        this.number_of_parts = numParts;
        this.image_url = setImgUrl;
        this.set_url = setUrl;
        this.modification_date = lastModifiedDt;
    }

    public long get_id() { return _id; }

    public void set_id(long _id) { this._id = _id; }

    public String getSet_number() {
        return set_number;
    }

    public void setSet_number(String setNum) {
        this.set_number = setNum;
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

    public int getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(int themeId) {
        this.theme_id = themeId;
    }

    public int getNumber_of_parts() {
        return number_of_parts;
    }

    public void setNumber_of_parts(int numParts) {
        this.number_of_parts = numParts;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String setImgUrl) {
        this.image_url = setImgUrl;
    }

    public String getSet_url() {
        return set_url;
    }

    public void setSet_url(String setUrl) {
        this.set_url = setUrl;
    }

    public String getModification_date() {
        return modification_date;
    }

    public void setModification_date(String lastModifiedDt) {
        this.modification_date = lastModifiedDt;
    }
}
