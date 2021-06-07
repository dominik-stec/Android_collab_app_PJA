package com.example.mylego.rest;

import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.domain.MinigfigsSingleSet;

public interface IFromRestCallback {

    void onGetOnePageResultFromRestSuccess(BricksSingleSet[] value);
    void onGetOnePageResultFromRestSuccess(MinigfigsSingleSet[] value);
}
