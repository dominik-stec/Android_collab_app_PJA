package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MinifigsSingleSet {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("set_num")
    @Expose
    String setNum;

    @SerializedName("set_name")
    @Expose
    String setName;

    @SerializedName("quantity")
    @Expose
    int quantity;

    @SerializedName("set_img_url")
    @Expose
    String setImgUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetNum() {
        return setNum;
    }

    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSetImgUrl() {
        return setImgUrl;
    }

    public void setSetImgUrl(String setImgUrl) {
        this.setImgUrl = setImgUrl;
    }


}
