package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Lego implements Serializable {

    @SerializedName("ext_ids")
    @Expose
    int[] extIds = null;

    @SerializedName("ext_descrs")
    @Expose
    String[][] extDescrs = null;

    public int[] getExtIds() {
        return extIds;
    }

    public void setExtIds(int[] extIds) {
        this.extIds = extIds;
    }

    public String[][] getExtDescrs() {
        return extDescrs;
    }

    public void setExtDescrs(String[][] extDescrs) {
        this.extDescrs = extDescrs;
    }
}
