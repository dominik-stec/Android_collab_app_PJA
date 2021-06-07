package com.example.mylego.rest;

import com.example.mylego.rest.domain.BricksSets;
import com.example.mylego.rest.domain.MinifigsSets;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {

    @GET("api/v3/lego/sets/")
    Call<BricksSets> getSetsByPageNumRest(@Header("Authorization") String auth, @Header("Accept") String type, @Query("page") int pageNum);

    @GET("api/v3/lego/sets/{set_num}/minifigs/")
    Call<MinifigsSets> getMinifigsSetByBricksSetNum(@Header("Authorization") String auth, @Header("Accept") String type, @Path("set_num") String setNum);

    @GET("api/v3/lego/sets/{set_num}/minifigs/")
    Call<MinifigsSets> getMinifigsSetByBricksSetNumByPage(@Header("Authorization") String auth, @Header("Accept") String type, @Path("set_num") String setNum, @Query("page") int pageNum);

}