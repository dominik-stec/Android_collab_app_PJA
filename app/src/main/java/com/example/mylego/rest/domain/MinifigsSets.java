package com.example.mylego.rest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MinifigsSets implements Serializable {

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
    MinigfigsSingleSet[] results = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public MinigfigsSingleSet[] getResults() {
        return results;
    }

    public void setResults(MinigfigsSingleSet[] results) {
        this.results = results;
    }
}
