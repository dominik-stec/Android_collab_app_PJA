package com.example.mylego.rest;

import retrofit2.*;

public interface IFromRestCallback {

    void onGetSetByIdRestSuccess(BricksSingleSet value);
    void onGetSetsRestSuccess(BricksSets value);
    void onFailure();
}
