package com.example.mylego.rest;

import android.content.Intent;

import java.io.IOException;

import com.example.mylego.services.RestService;
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

public class RestCtrl implements Callback<BricksSingleSet> {

    static final java.lang.String BASE_URL = "https://rebrickable.com/";
    RestApi restApi;
    BricksSingleSet bricksSingleSet;

    CustomCallback customCallback;

    public RestCtrl(CustomCallback customCallback) {
        start();
        this.customCallback = customCallback;
    }

    public BricksSingleSet getById(java.lang.String id) {
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
        OkHttpClient httpHeaderConf = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
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
        Builder clientBuilder = new OkHttpClient.Builder();
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
            //System.out.println("test" + response.message());
            //BricksSingleSet bricksSingleSetObj = response.body();

            //System.out.println("Bricks set name: " + bricksSingleSetObj.name);
            System.out.println("result!!!!!!!!!!!");
            System.out.println("name " + response.body().getName());
            bricksSingleSet = response.body();
            //this.bricksSingleSet = bricksSingleSetObj;

//            Intent i = new Intent();
//            i.putExtra("Bricks name", response.body().getName());
//            // potentially add data to the intent
////        i.putExtra("KEY1", "Value to be used by the service");
//            startService(i);

            customCallback.onSucess(bricksSingleSet);

        } else {
            System.out.println(response.errorBody().toString());
            System.out.println("onResponse error");
            System.out.println("error response code: " + response.code());
        }
    }

    @Override
    public void onFailure(Call<BricksSingleSet> call, Throwable t) {
        t.printStackTrace();
        System.out.println("onFailure error");

    }

}
