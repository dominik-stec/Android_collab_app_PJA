package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Part implements Serializable {

    Integer id;

    @SerializedName("set_num")
    @Expose
    String setNum;

    @SerializedName("part_num")
    @Expose
    String partNum;

    @SerializedName("name")
    @Expose
    String partName;

    @SerializedName("part_cat_id")
    @Expose
    Integer partCatId;

    @SerializedName("part_url")
    @Expose
    String partUrl;

    @SerializedName("part_img_url")
    @Expose
    String partImgUrl;

    @SerializedName("color")
    @Expose
    String partColor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSetNum() {
        return setNum;
    }

    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }

    public String getPartNum() {
        return partNum;
    }

    public void setPartNum(String partNum) {
        this.partNum = partNum;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Integer getPartCatId() {
        return partCatId;
    }

    public void setPartCatId(Integer partCatId) {
        this.partCatId = partCatId;
    }

    public String getPartUrl() {
        return partUrl;
    }

    public void setPartUrl(String partUrl) {
        this.partUrl = partUrl;
    }

    public String getPartImgUrl() {
        return partImgUrl;
    }

    public void setPartImgUrl(String partImgUrl) {
        this.partImgUrl = partImgUrl;
    }

    public String getPartColor() {
        return partColor;
    }

    public void setPartColor(String partColor) {
        this.partColor = partColor;
    }
}
