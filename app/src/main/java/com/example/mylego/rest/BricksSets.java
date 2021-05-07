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
    java.lang.String next;
    @SerializedName("previous")
    @Expose
    java.lang.String previous;
    @SerializedName("results")
    @Expose
    List<String> results = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public java.lang.String getName() {
        return next;
    }

    public void setName(java.lang.String name) {
        this.next = name;
    }

    public java.lang.String getPrevious() {
        return previous;
    }

    public void setPrevious(java.lang.String previous) {
        this.previous = previous;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
