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
    List<BricksSingleSet> results = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return next;
    }

    public void setName(String name) {
        this.next = name;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<BricksSingleSet> getResults() {
        return results;
    }

    public void setResults(List<BricksSingleSet> results) {
        this.results = results;
    }
}
