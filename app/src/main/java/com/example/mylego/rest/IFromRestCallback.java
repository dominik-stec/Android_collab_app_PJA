package com.example.mylego.rest;

import retrofit2.*;

public interface IFromRestCallback {

    void onSucess(BricksSingleSet value);
    void onFailure();
}
