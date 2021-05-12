package com.example.mylego.rest.controllers;

import android.util.Log;

import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.BricksSets;
import com.example.mylego.rest.domain.BricksSingleSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestOnePageBricksCtrl extends RestCtrl implements Callback<BricksSets> {

    BricksSets bricksSets;

    com.example.mylego.rest.IFromRestCallback IFromRestCallback;

    public RestOnePageBricksCtrl(IFromRestCallback IFromRestCallback) {
        super.start();
        this.IFromRestCallback = IFromRestCallback;
        bricksSets = new BricksSets();
//        Call<BricksSets> call = restApi.getSetsRest(TOKEN_ACCESS_KEY, "application/json");
//        call.enqueue(this);
        Call<BricksSets> call = restApi.getSetsByPageNumRest(TOKEN_ACCESS_KEY, "application/json", 1);
        call.enqueue(this);
    }

    public static int max_iter_num = 10;
    public static int speed_rest_read = 2000;
    public static int to_insert_row_count = max_iter_num * 100;
    public static int counter = 0;

    @Override
    public void onResponse(Call<BricksSets> call, Response<BricksSets> response) {

        if(response.isSuccessful()) {

            bricksSets.setResults(response.body().getResults());
            Log.d("result one page", "get one page brickset.result()");
            String nextLink = response.body().getNext();
            if(nextLink != null) {

                IFromRestCallback.onGetOnePageResultFromRestSuccess(bricksSets.getResults());


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
                    IFromRestCallback.onGetOnePageResultFromRestSuccess(bricksSets.getResults());
                    Log.i("REST one page", "onResponse method pass but with limit maxIterNum one page");
                    return;
                }

                double progress = Math.round(((double)pageNum/max_iter_num) * 100);
                Log.d("rest_loop one page", "loop in work one page " + progress + " %");

                Call<BricksSets> callLoop = restApi.getSetsByPageNumRest(TOKEN_ACCESS_KEY, "application/json", pageNum);
                callLoop.enqueue(this);

            } else if(nextLink == null) {
                IFromRestCallback.onGetOnePageResultFromRestSuccess(bricksSets.getResults());
                Log.i("REST for all full ok", "onResponse method pass one page all data from rest");
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
        Log.e("REST error one page","onFailure method error one page");
        t.printStackTrace();
    }

    public BricksSingleSet[] getOnePageBricksList() {
        return bricksSets.getResults();
    }

}
