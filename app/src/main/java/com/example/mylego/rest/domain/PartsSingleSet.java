package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartsSingleSet {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("inv_part_id")
    @Expose
    int inv_part_id;

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
    int quantity;

    @SerializedName("is_spare")
    @Expose
    int isSpare;

    @SerializedName("element_id")
    @Expose
    int elementId;

    @SerializedName("num_sets")
    @Expose
    int numSets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInv_part_id() {
        return inv_part_id;
    }

    public void setInv_part_id(int inv_part_id) {
        this.inv_part_id = inv_part_id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIsSpare() {
        return isSpare;
    }

    public void setIsSpare(int isSpare) {
        this.isSpare = isSpare;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public int getNumSets() {
        return numSets;
    }

    public void setNumSets(int numSets) {
        this.numSets = numSets;
    }
}
