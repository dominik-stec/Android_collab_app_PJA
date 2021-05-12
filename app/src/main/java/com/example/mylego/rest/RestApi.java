package com.example.mylego.rest;

import com.example.mylego.rest.domain.BricksSets;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RestApi {

    @GET("api/v3/lego/sets/")
    Call<BricksSets> getSetsByPageNumRest(@Header("Authorization") String auth, @Header("Accept") String type, @Query("page") int pageNum);
}