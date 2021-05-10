package com.example.mylego.rest.controllers;

import android.util.Log;

import com.example.mylego.database.DbManager;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.BricksSets;
import com.example.mylego.rest.domain.BricksSingleSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestAllBricksCtrl extends RestCtrl implements Callback<BricksSets> {

    BricksSets bricksSets;

    com.example.mylego.rest.IFromRestCallback IFromRestCallback;

    public RestAllBricksCtrl(IFromRestCallback IFromRestCallback) {
        super.start();
        this.IFromRestCallback = IFromRestCallback;
        Call<BricksSets> call = restApi.getSetsRest(TOKEN_ACCESS_KEY, "application/json");
        call.enqueue(this);
    }

    public BricksSets getSets() {
        return bricksSets;
    }
    public List<BricksSingleSet[]> getAllSets() {
        return listOfListBricks;
    }


    List<BricksSingleSet[]> listOfListBricks= new ArrayList<BricksSingleSet[]>();

    @Override
    public void onResponse(Call<BricksSets> call, Response<BricksSets> response) {
    //TODO disable rest
        ////////////////////////////////
        if(response.isSuccessful()) {

                listOfListBricks.add(response.body().getResults());
                String nextLink = response.body().getNext();
                if(nextLink != null) {
                    String nextPageRaw = nextLink.replaceAll("[^0-9]", "");
                    String nextPage = nextPageRaw.substring(1);
                    int pageNum = Integer.parseInt(nextPage);
                    Log.d("loop", "loop in work  " + pageNum);
                    //177 loop iteration
                    try{
                        Thread.sleep(2000);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    Call<BricksSets> callLoop = restApi.getSetsByPageNumRest(TOKEN_ACCESS_KEY, "application/json", pageNum);
                    callLoop.enqueue(this);
                } else if(nextLink == null) {
                    IFromRestCallback.onGetSetsRestAllSuccess(listOfListBricks);
                    Log.i("REST for all full ok", "onResponse method pass");
                }
/////////////////////////////


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
