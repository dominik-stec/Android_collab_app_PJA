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

    @SerializedName("ext_descrs")
    @Expose
    ExtDescrs extDescrs;
}
