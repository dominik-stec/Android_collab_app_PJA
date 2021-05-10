package com.example.mylego.rest;

import android.util.Log;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestBricksByIdCtrl extends RestCtrl implements Callback<BricksSingleSet> {

    BricksSingleSet bricksSingleSet;

    IFromRestCallback IFromRestCallback;

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
