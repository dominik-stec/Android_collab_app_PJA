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

public class RestAllBricksCtrl implements Callback<BricksSingleSet> {

    static final String BASE_URL = "https://rebrickable.com/";
    RestApi restApi;
    BricksSingleSet bricksSingleSet;

    IFromRestCallback IFromRestCallback;

    public RestAllBricksCtrl(IFromRestCallback IFromRestCallback) {
        start();
        this.IFromRestCallback = IFromRestCallback;
    }

    public BricksSingleSet getById(String id) {
        start();
        Call<BricksSingleSet> call = restApi.runRest("key cae9480418c5c7f7ef9a76142f8f5f48", "application/json", id);
        call.enqueue(this);
        return bricksSingleSet;
    }

    public void start() {

        Gson gson = initGson();
        Builder clientBuilder = initLogBuilder();
        OkHttpClient httpHeaderConf = initHttpHeader();
        this.restApi = initRetrofit(httpHeaderConf, gson, clientBuilder);
    }

    private RestApi initRetrofit(OkHttpClient httpHeaderConf, Gson gson, Builder clientBuilder) {
        Retrofit retrofit = new Retrofit.Builder()
                    .client(httpHeaderConf)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(BASE_URL)
                    .client(clientBuilder.build())
                    .build();
        RestApi restApi = retrofit.create(RestApi.class);
        return restApi;
    }

    private OkHttpClient initHttpHeader() {
        OkHttpClient httpHeaderConf = new Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "key cae9480418c5c7f7ef9a76142f8f5f48")
                        .addHeader("Accept", "application/json")
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        return httpHeaderConf;
    }

    private Builder initLogBuilder() {
        Builder clientBuilder = new Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);
        return clientBuilder;
    }

    private Gson initGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Override
    public void onResponse(Call<BricksSingleSet> call, Response<BricksSingleSet> response) {
        if(response.isSuccessful()) {

            bricksSingleSet = response.body();
            IFromRestCallback.onSucess(bricksSingleSet);
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
