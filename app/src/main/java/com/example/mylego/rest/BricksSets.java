package com.example.mylego.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BricksSets implements Serializable {

    @SerializedName("count")
    @Expose
    int count;
    @SerializedName("next")
    @Expose
    String next;
    @SerializedName("previous")
    @Expose
    String previous;
    @SerializedName("results")
    @Expose
    BricksSingleSet[] results = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public java.lang.String getNext() {
        return next;
    }

    public void setNext(java.lang.String next) {
        this.next = next;
    }

    public java.lang.String getPrevious() {
        return previous;
    }

    public void setPrevious(java.lang.String previous) {
        this.previous = previous;
    }

    public BricksSingleSet[] getResults() {
        return results;
    }

    public void setResults(BricksSingleSet[] results) {
        this.results = results;
    }
}
