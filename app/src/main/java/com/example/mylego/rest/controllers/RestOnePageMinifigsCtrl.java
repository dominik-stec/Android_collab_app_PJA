package com.example.mylego.rest.controllers;

import android.util.Log;

import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.BricksSets;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.domain.MinifigsSets;
import com.example.mylego.rest.domain.MinigfigsSingleSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestOnePageMinifigsCtrl extends RestCtrl implements Callback<MinifigsSets> {

    MinifigsSets minifigsSets;

    IFromRestCallback IFromRestCallback;

    public static String SET_NUM =  "0011-2";

    public RestOnePageMinifigsCtrl(IFromRestCallback IFromRestCallback) {

        super.start();

        this.IFromRestCallback = IFromRestCallback;

        minifigsSets = new MinifigsSets();

        Call<MinifigsSets> call = restApi.getMinifigsSetByBricksSetNum(TOKEN_ACCESS_KEY, "application/json", SET_NUM);
        call.enqueue(this);
    }


    // number of rest iteration -> 1 iteration == 100 Bricks sets read from API
    public static int max_iter_num = 10;

    // how fast REST should read data from API
    public static int speed_rest_read = 2000;


    // do not change
    public static int to_insert_row_count = max_iter_num * 100;
    // do not change
    public static int counter = 0;

    @Override
    public void onResponse(Call<MinifigsSets> call, Response<MinifigsSets> response) {

        if(response.isSuccessful()) {

            minifigsSets.setResults(response.body().getResults());

            String nextLink = response.body().getNext();

            if(nextLink != null) {

                IFromRestCallback.onGetOnePageResultFromRestSuccess(minifigsSets.getResults());

                String nextPageRaw = nextLink.replaceAll("[^0-9]", "");
                String nextPage = nextPageRaw.substring(1);
                int pageNum = Integer.parseInt(nextPage);

                //177 loop iteration
                try{
                    Thread.sleep(speed_rest_read);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

                //177 pages max
                if(pageNum > max_iter_num){
                    IFromRestCallback.onGetOnePageResultFromRestSuccess(minifigsSets.getResults());
                    return;
                }

                Call<MinifigsSets> callLoop = restApi.getMinifigsSetByBricksSetNumByPage(TOKEN_ACCESS_KEY, "application/json", SET_NUM, pageNum);
                callLoop.enqueue(this);

            } else if(nextLink == null) {
                IFromRestCallback.onGetOnePageResultFromRestSuccess(minifigsSets.getResults());
            }


        } else {
            System.out.println(response.errorBody().toString());
            Log.e("REST error", "onResponse method error in MinifigsSets");
            Log.e("error response code", String.valueOf(response.code()));
        }
    }

    @Override
    public void onFailure(Call<MinifigsSets> call, Throwable t) {
        Log.e("REST error one page","onFailure method error one page read for MinifigsSets");
        t.printStackTrace();
    }

    public MinigfigsSingleSet[] getOnePageSetList() {
        return minifigsSets.getResults();
    }

}
