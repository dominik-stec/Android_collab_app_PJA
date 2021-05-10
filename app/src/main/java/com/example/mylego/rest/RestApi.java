package com.example.mylego.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface RestApi {

    @GET("api/v3/lego/sets/{id}/")
    Call<BricksSingleSet> runRest(@Header("Authorization") String auth, @Header("Accept") String type, @Path("id") String id);


    @GET("api/v3/lego/sets/")
    Call<BricksSets> getSetsRest(@Header("Authorization") String auth, @Header("Accept") String type);
}