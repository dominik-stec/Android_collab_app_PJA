package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExternalIdsColor implements Serializable {

    @SerializedName("BrickLink")
    @Expose
    BrickLink brickLink;

    @SerializedName("BrickOwl")
    @Expose
    BrickOwl brickOwl;

    @SerializedName("LEGO")
    @Expose
    Lego lego;

    @SerializedName("Peeron")
    @Expose
    Peeron peeron;

    @SerializedName("LDraw")
    @Expose
    LDraw lDraw;

    public BrickLink getBrickLink() {
        return brickLink;
    }

    public void setBrickLink(BrickLink brickLink) {
        this.brickLink = brickLink;
    }

    public BrickOwl getBrickOwl() {
        return brickOwl;
    }

    public void setBrickOwl(BrickOwl brickOwl) {
        this.brickOwl = brickOwl;
    }

    public Lego getLego() {
        return lego;
    }

    public void setLego(Lego lego) {
        this.lego = lego;
    }

    public Peeron getPeeron() {
        return peeron;
    }

    public void setPeeron(Peeron peeron) {
        this.peeron = peeron;
    }

    public LDraw getlDraw() {
        return lDraw;
    }

    public void setlDraw(LDraw lDraw) {
        this.lDraw = lDraw;
    }
}
