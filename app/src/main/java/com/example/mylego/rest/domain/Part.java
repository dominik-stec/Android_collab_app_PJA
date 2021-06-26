package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Part implements Serializable {

    Integer _id;

    String set_num;

    @SerializedName("part_num")
    @Expose
    String part_num;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("part_cat_id")
    @Expose
    Integer part_cat_id;

    @SerializedName("part_url")
    @Expose
    String part_url;

    @SerializedName("part_img_url")
    @Expose
    String part_img_url;

    String color_name;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer id) {
        this._id = id;
    }

    public String getSet_num() {
        return set_num;
    }

    public void setSet_num(String setNum) {
        this.set_num = setNum;
    }

    public String getPart_num() {
        return part_num;
    }

    public void setPart_num(String partNum) {
        this.part_num = partNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String partName) {
        this.name = partName;
    }

    public Integer getPart_cat_id() {
        return part_cat_id;
    }

    public void setPart_cat_id(Integer partCatId) {
        this.part_cat_id = partCatId;
    }

    public String getPart_url() {
        return part_url;
    }

    public void setPart_url(String partUrl) {
        this.part_url = partUrl;
    }

    public String getPart_img_url() {
        return part_img_url;
    }

    public void setPart_img_url(String partImgUrl) {
        this.part_img_url = partImgUrl;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String partColor) {
        this.color_name = partColor;
    }
}
