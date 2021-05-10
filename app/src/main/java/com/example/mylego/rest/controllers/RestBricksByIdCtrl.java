package com.example.mylego.rest.controllers;

import android.util.Log;

import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.BricksSingleSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestBricksByIdCtrl extends RestCtrl implements Callback<BricksSingleSet> {

    BricksSingleSet bricksSingleSet;

    com.example.mylego.rest.IFromRestCallback IFromRestCallback;

    public RestBricksByIdCtrl(IFromRestCallback IFromRestCallback) {
        this.IFromRestCallback = IFromRestCallback;
        super.start();
    }

    public BricksSingleSet getById(java.lang.String id) {
        Call<BricksSingleSet> call = restApi.runRest(TOKEN_ACCESS_KEY, "application/json", id);
        call.enqueue(this);
        return bricksSingleSet;
    }

    @Override
    public void onResponse(Call<BricksSingleSet> call, Response<BricksSingleSet> response) {
        if(response.isSuccessful()) {

            bricksSingleSet = response.body();
            IFromRestCallback.onGetSetByIdRestSuccess(bricksSingleSet);
            Log.i("REST ok", "onResponse method pass");

        } else {
            System.out.println(response.errorBody().toString());
            Log.e("REST error", "onResponse method error");
            Log.e("error response code", String.valueOf(response.code()));
        }
    }

    @Override
    public void onFailure(Call<BricksSingleSet> call, Throwable t) {
        Log.e("REST error","onFailure method error");
        t.printStackTrace();
    }

}
