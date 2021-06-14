package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExternalIds implements Serializable {

    @SerializedName("BrickOwl")
    @Expose
    String[] brickOwl = null;

    @SerializedName("LDraw")
    @Expose
    String[] lDraw = null;

    @SerializedName("LEGO")
    @Expose
    String[] lego = null;

    @SerializedName("Peeron")
    @Expose
    String[] peeron = null;

    public String[] getBrickOwl() {
        return brickOwl;
    }

    public void setBrickOwl(String[] brickOwl) {
        this.brickOwl = brickOwl;
    }

    public String[] getlDraw() {
        return lDraw;
    }

    public void setlDraw(String[] lDraw) {
        this.lDraw = lDraw;
    }

    public String[] getLego() {
        return lego;
    }

    public void setLego(String[] lego) {
        this.lego = lego;
    }

    public String[] getPeeron() {
        return peeron;
    }

    public void setPeeron(String[] peeron) {
        this.peeron = peeron;
    }
}
