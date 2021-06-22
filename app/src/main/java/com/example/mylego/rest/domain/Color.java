package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Color implements Serializable {

    @SerializedName("id")
    @Expose
    Integer id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("rgb")
    @Expose
    String rgb;

    @SerializedName("is_trans")
    @Expose
    boolean isTrans;

    @SerializedName("external_ids")
    @Expose
    ExternalIdsColor externalIdsColor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public boolean isTrans() {
        return isTrans;
    }

    public void setTrans(boolean trans) {
        isTrans = trans;
    }

    public ExternalIdsColor getExternalIdsColor() {
        return externalIdsColor;
    }

    public void setExternalIdsColor(ExternalIdsColor externalIdsColor) {
        this.externalIdsColor = externalIdsColor;
    }
}
