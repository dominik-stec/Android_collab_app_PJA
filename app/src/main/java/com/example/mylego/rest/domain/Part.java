package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Part implements Serializable {

    @SerializedName("part_num")
    @Expose
    String partNum;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("part_cat_id")
    @Expose
    int partCatId;

    @SerializedName("part_url")
    @Expose
    String partUrl;

    @SerializedName("part_img_url")
    @Expose
    String partImgUrl;

    @SerializedName("external_ids")
    @Expose
    ExternalIds externalIds;

    @SerializedName("print_of")
    @Expose
    String printOf;

    public String getPartNum() {
        return partNum;
    }

    public void setPartNum(String partNum) {
        this.partNum = partNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartCatId() {
        return partCatId;
    }

    public void setPartCatId(int partCatId) {
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

    public ExternalIds getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(ExternalIds externalIds) {
        this.externalIds = externalIds;
    }

    public String getPrintOf() {
        return printOf;
    }

    public void setPrintOf(String printOf) {
        this.printOf = printOf;
    }
}
