package com.example.mylego.rest;

import com.example.mylego.rest.domain.BricksSets;
import com.example.mylego.rest.domain.BricksSingleSet;

import java.util.List;

public interface IFromRestCallback {

    void onGetSetByIdRestSuccess(BricksSingleSet value);
    void onGetSetsRestSuccess(BricksSets value);
    void onGetSetsRestAllSuccess(List<BricksSingleSet[]> value);
    void onFailure();
}
