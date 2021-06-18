package com.example.mylego.rest.controllers;

import android.content.Intent;
import android.util.Log;

import com.example.mylego.database.DbSetNumManager;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.MinifigsSets;
import com.example.mylego.rest.domain.MinifigsSingleSet;
import com.example.mylego.rest.domain.Part;
import com.example.mylego.rest.domain.PartsSets;
import com.example.mylego.services.RestService;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestOnePagePartsCtrl extends RestCtrl implements Callback<PartsSets> {

    PartsSets partsSets;

    IFromRestCallback IFromRestCallback;

    public static String SET_NUM =  "0011-2";

    DbSetNumManager dbSetNum;
    ArrayList<String> setNumList;

    public RestOnePagePartsCtrl(IFromRestCallback IFromRestCallback) {

        super.start();

        this.IFromRestCallback = IFromRestCallback;

        dbSetNum = new DbSetNumManager(RestService.getContext());
        setNumList = dbSetNum.selectAllQueries();

        partsSets = new PartsSets();

        Call<PartsSets> call = restApi.getPartsSetByBricksSetNum(TOKEN_ACCESS_KEY, "application/json", SET_NUM);
        call.enqueue(this);
    }

    // cut maximum number of rest calls
    public static int rest_limit_weight = 20;

    // number of rest iteration -> 1 iteration == 100 Bricks sets read from API
    public static int max_iter_num = RestOnePageBricksCtrl.to_insert_row_count / rest_limit_weight;

    // how fast REST should read data from API
    public static int speed_rest_read = 500;


    // do not change
//    public static int to_insert_row_count = max_iter_num * 100;
    // do not change
    public static int counter = 0;

    public static int inc = 1;

    String setNum = "";

    @Override
    public void onResponse(Call<PartsSets> call, Response<PartsSets> response) {

        if(response.isSuccessful()) {

//            //read all need data from rest
            if(RestLimiter.limiter >= RestLimiter.rest_limit){
                Intent rest = new Intent(RestService.getContext(), RestService.class);
                RestService.getContext().stopService(rest);
                call.cancel();
                return;
            }

            partsSets.setResults(response.body().getResults());

            HashMap<Long, String> setNumMap = new HashMap<>();


            String nextLink = response.body().getNext();
            String previousLink = response.body().getPrevious();
            int count = response.body().getCount();

            //loop iteration speed
            try{
                Thread.sleep(speed_rest_read);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            if(nextLink==null && previousLink==null && count==0) {
                ++inc;
                try{
                    setNum = setNumList.get(inc);
                } catch(Exception e) {
                    Log.d("PartsCtrl all null","exception from parts controller");
                    return;
                }


                Call<PartsSets> callLoop = restApi.getPartsSetByBricksSetNumByPage(TOKEN_ACCESS_KEY, "application/json", setNum,1);
                callLoop.enqueue(this);
            }



            if(nextLink != null) {

                IFromRestCallback.onGetOnePageResultPartsFromRestSuccess(partsSets.getResults());

                String nextPageRaw = nextLink.replaceAll("[^0-9]", "");
                String nextPage = nextPageRaw.substring(1);
                int pageNum = Integer.parseInt(nextPage);



                try{
                    setNum = setNumList.get(inc);
                } catch(Exception e) {
                    Log.d("MinifigsCtrl1","exception from minifigs controller");
                    return;
                }

//                --to_insert_row_count;

                Call<PartsSets> callLoop = restApi.getPartsSetByBricksSetNumByPage(TOKEN_ACCESS_KEY, "application/json", setNum, pageNum);
                callLoop.enqueue(this);

            } else if(nextLink == null && count!=0) {
                IFromRestCallback.onGetOnePageResultPartsFromRestSuccess(partsSets.getResults());

                try{
                    setNum = setNumList.get(inc);
                    ++inc;
                } catch(Exception e) {
                    Log.d("PartsCtrl2","exception from parts controller");
                    throw(e);
                }

                int pageNum = 1;
                Call<PartsSets> callLoop = restApi.getPartsSetByBricksSetNumByPage(TOKEN_ACCESS_KEY, "application/json", setNum, pageNum);
                callLoop.enqueue(this);

            }


        } else {
            System.out.println(response.errorBody().toString());
            Log.e("REST error", "onResponse method error in MinifigsSets");
            Log.e("error response code", String.valueOf(response.code()));
        }
    }

    @Override
    public void onFailure(Call<PartsSets> call, Throwable t) {
        Log.e("REST error one page","onFailure method error one page read for PartSets");
        t.printStackTrace();
    }

}
