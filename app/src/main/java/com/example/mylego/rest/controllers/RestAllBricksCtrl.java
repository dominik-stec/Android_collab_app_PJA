package com.example.mylego.rest.controllers;

import android.util.Log;

import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.BricksSets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestAllBricksCtrl extends RestCtrl implements Callback<BricksSets> {

    BricksSets bricksSets;

    com.example.mylego.rest.IFromRestCallback IFromRestCallback;

    public RestAllBricksCtrl(IFromRestCallback IFromRestCallback) {
        super.start();
        this.IFromRestCallback = IFromRestCallback;
        Call<BricksSets> call = restApi.getSetsRest(TOKEN_ACCESS_KEY, "application/json");
        call.enqueue(this);
    }

    public BricksSets getSets() {
        return bricksSets;
    }

    @Override
    public void onResponse(Call<BricksSets> call, Response<BricksSets> response) {
        if(response.isSuccessful()) {

            bricksSets = response.body();
            IFromRestCallback.onGetSetsRestSuccess(bricksSets);
            Log.i("REST ok", "onResponse method pass");

        } else {
            System.out.println(response.errorBody().toString());
            Log.e("REST error", "onResponse method error");
            Log.e("error response code", String.valueOf(response.code()));
        }
    }

    @Override
    public void onFailure(Call<BricksSets> call, Throwable t) {
        Log.e("REST error","onFailure method error");
        t.printStackTrace();
    }

}
