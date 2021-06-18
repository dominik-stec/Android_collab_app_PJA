package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PartsSingleSet implements Serializable {

    @SerializedName("id")
    @Expose
    Integer id;

    @SerializedName("inv_part_id")
    @Expose
    Integer invPartId;

    @SerializedName("part")
    @Expose
    Part part = null;

    @SerializedName("color")
    @Expose
    Color color = null;

    @SerializedName("set_sum")
    @Expose
    String setNum;

    @SerializedName("quantity")
    @Expose
    Integer quantity;

    @SerializedName("is_spare")
    @Expose
    boolean isSpare;

    @SerializedName("element_id")
    @Expose
    String elementId;

    @SerializedName("num_sets")
    @Expose
    Integer numSets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvPartId() {
        return invPartId;
    }

    public void setInvPartId(Integer invPartId) {
        this.invPartId = invPartId;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getSetNum() {
        return setNum;
    }

    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public Integer getNumSets() {
        return numSets;
    }

    public void setNumSets(Integer numSets) {
        this.numSets = numSets;
    }
}
