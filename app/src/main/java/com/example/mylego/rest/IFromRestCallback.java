package com.example.mylego.rest;

import com.example.mylego.rest.domain.BricksSets;
import com.example.mylego.rest.domain.BricksSingleSet;

public interface IFromRestCallback {

    void onGetSetByIdRestSuccess(BricksSingleSet value);
    void onGetSetsRestSuccess(BricksSets value);
    void onFailure();
}
