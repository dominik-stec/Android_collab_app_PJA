package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LDraw implements Serializable {

    @SerializedName("ext_ids")
    @Expose
    Integer[] extIds = null;

    @SerializedName("ext_descrs")
    @Expose
    String[][] extDescrs = null;

    public Integer[] getExtIds() {
        return extIds;
    }

    public void setExtIds(Integer[] extIds) {
        this.extIds = extIds;
    }

    public String[][] getExtDescrs() {
        return extDescrs;
    }

    public void setExtDescrs(String[][] extDescrs) {
        this.extDescrs = extDescrs;
    }
}
