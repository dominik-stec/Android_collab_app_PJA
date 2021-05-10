package com.example.mylego.rest;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAllBricksCtrl extends RestCtrl implements Callback<BricksSets> {

    BricksSets bricksSets;

    IFromRestCallback IFromRestCallback;

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
