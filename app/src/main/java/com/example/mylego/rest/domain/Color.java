package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Color implements Serializable {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("rgb")
    @Expose
    String rgb;

    @SerializedName("is_trans")
    @Expose
    boolean isTrans;

    @SerializedName("external_ids")
    @Expose
    ExternalIdsColor externalIdsColor;

}
