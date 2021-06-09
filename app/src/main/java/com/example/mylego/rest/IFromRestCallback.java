package com.example.mylego.rest;

import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.domain.MinifigsSingleSet;

public interface IFromRestCallback {

    void onGetOnePageResultFromRestSuccess(BricksSingleSet[] value);
    void onGetOnePageResultMinifigsFromRestSuccess(MinifigsSingleSet[] value);
}
