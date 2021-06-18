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
    Integer partCatId;

    @SerializedName("part_url")
    @Expose
    String partUrl;

    @SerializedName("part_img_url")
    @Expose
    String partImgUrl;

    @SerializedName("external_ids")
    @Expose
    ExternalIdsPart externalIds;

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

    public ExternalIdsPart getExternalIdsPart() {
        return externalIds;
    }

    public void setExternalIdsPart(ExternalIdsPart externalIdsPart) {
        this.externalIds = externalIdsPart;
    }

    public String getPrintOf() {
        return printOf;
    }

    public void setPrintOf(String printOf) {
        this.printOf = printOf;
    }
}
