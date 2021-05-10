package com.example.mylego.rest;

import com.example.mylego.rest.domain.BricksSets;
import com.example.mylego.rest.domain.BricksSingleSet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {

    @GET("api/v3/lego/sets/{id}/")
    Call<BricksSingleSet> getSetByIdRest(@Header("Authorization") String auth, @Header("Accept") String type, @Path("id") String id);

    @GET("api/v3/lego/sets/")
    Call<BricksSets> getSetsRest(@Header("Authorization") String auth, @Header("Accept") String type);

    @GET("api/v3/lego/sets/")
    Call<BricksSets> getSetsByPageNumRest(@Header("Authorization") String auth, @Header("Accept") String type, @Query("page") int pageNum);
}