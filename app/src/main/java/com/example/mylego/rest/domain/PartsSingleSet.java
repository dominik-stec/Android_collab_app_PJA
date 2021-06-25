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
    Integer inv_part_id;

    @SerializedName("part")
    @Expose
    Part part = null;

    @SerializedName("color")
    @Expose
    Color color = null;

    @SerializedName("set_num")
    @Expose
    String set_num;

    @SerializedName("quantity")
    @Expose
    Integer quantity;

    @SerializedName("is_spare")
    @Expose
    boolean is_spare;

    @SerializedName("element_id")
    @Expose
    String element_id;

    @SerializedName("num_sets")
    @Expose
    Integer num_sets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInv_part_id() {
        return inv_part_id;
    }

    public void setInv_part_id(Integer invPartId) {
        this.inv_part_id = invPartId;
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

    public String getSet_num() {
        return set_num;
    }

    public void setSet_num(String setNum) {
        this.set_num = setNum;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isIs_spare() {
        return is_spare;
    }

    public void setIs_spare(boolean spare) {
        is_spare = spare;
    }

    public String getElement_id() {
        return element_id;
    }

    public void setElement_id(String elementId) {
        this.element_id = elementId;
    }

    public Integer getNum_sets() {
        return num_sets;
    }

    public void setNum_sets(Integer numSets) {
        this.num_sets = numSets;
    }
}
