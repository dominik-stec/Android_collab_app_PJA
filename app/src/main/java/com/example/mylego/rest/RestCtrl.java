package com.example.mylego.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestCtrl {

    static final String BASE_URL = "https://rebrickable.com/";
    static final String TOKEN_ACCESS_KEY = "key cae9480418c5c7f7ef9a76142f8f5f48";

    RestApi restApi;
    //BricksSingleSet bricksSingleSet;

    //IFromRestCallback IFromRestCallback;

//    public RestBricksByIdCtrl(IFromRestCallback IFromRestCallback) {
//        start();
//        this.IFromRestCallback = IFromRestCallback;
//    }
//
//    public BricksSingleSet getById(java.lang.String id) {
//        start();
//        Call<BricksSingleSet> call = restApi.runRest("key cae9480418c5c7f7ef9a76142f8f5f48", "application/json", id);
//        call.enqueue(this);
//        return bricksSingleSet;
//    }

    public void start() {

        Gson gson = initGson();
        OkHttpClient.Builder clientBuilder = initLogBuilder();
        OkHttpClient httpHeaderConf = initHttpHeader();
        this.restApi = initRetrofit(httpHeaderConf, gson, clientBuilder);
    }

    private RestApi initRetrofit(OkHttpClient httpHeaderConf, Gson gson, OkHttpClient.Builder clientBuilder) {
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

    private OkHttpClient.Builder initLogBuilder() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
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

}
