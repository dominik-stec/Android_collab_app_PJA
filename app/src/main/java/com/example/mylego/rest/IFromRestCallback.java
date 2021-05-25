package com.example.mylego.rest;

import com.example.mylego.rest.domain.BricksSingleSet;

public interface IFromRestCallback {

    void onGetOnePageResultFromRestSuccess(BricksSingleSet[] value);
}
