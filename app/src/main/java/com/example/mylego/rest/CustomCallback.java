package com.example.mylego.rest;

import retrofit2.*;

public interface CustomCallback {

    void onSucess(BricksSingleSet value);
    void onFailure();
}
