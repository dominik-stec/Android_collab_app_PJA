package com.example.mylego.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface RestApi {

    @GET("api/v3/lego/sets/{id}/")
    Call<BricksSingleSet> runRest(@Header("Authorization") java.lang.String auth, @Header("Accept") java.lang.String type, @Path("id") java.lang.String id);
}