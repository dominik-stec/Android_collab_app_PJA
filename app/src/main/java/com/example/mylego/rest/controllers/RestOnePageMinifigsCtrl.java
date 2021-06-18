package com.example.mylego.rest.controllers;

import android.util.Log;
import com.example.mylego.database.DbSetNumManager;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.MinifigsSets;
import com.example.mylego.rest.domain.MinifigsSingleSet;
import com.example.mylego.services.RestService;
import java.util.ArrayList;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestOnePageMinifigsCtrl extends RestCtrl implements Callback<MinifigsSets> {

    MinifigsSets minifigsSets;

    IFromRestCallback IFromRestCallback;

    public static String SET_NUM =  "0011-2";

    DbSetNumManager dbSetNum;
    ArrayList<String> setNumList;

    public RestOnePageMinifigsCtrl(IFromRestCallback IFromRestCallback) {

        super.start();

        this.IFromRestCallback = IFromRestCallback;

        dbSetNum = new DbSetNumManager(RestService.getContext());
        setNumList = dbSetNum.selectAllQueries();

        minifigsSets = new MinifigsSets();

        Call<MinifigsSets> call = restApi.getMinifigsSetByBricksSetNum(TOKEN_ACCESS_KEY, "application/json", SET_NUM);
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
    public void onResponse(Call<MinifigsSets> call, Response<MinifigsSets> response) {

        if(response.isSuccessful()) {


            //read all need data from rest
            if(inc >= max_iter_num){
                return;
            }

            HashMap<Long, String> setNumMap = new HashMap<>();

            minifigsSets.setResults(response.body().getResults());

            String nextLink = response.body().getNext();

            //loop iteration speed
            try{
                Thread.sleep(speed_rest_read);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            if(nextLink != null) {

                IFromRestCallback.onGetOnePageResultMinifigsFromRestSuccess(minifigsSets.getResults());

                String nextPageRaw = nextLink.replaceAll("[^0-9]", "");
                String nextPage = nextPageRaw.substring(1);
                int pageNum = Integer.parseInt(nextPage);



                try{
                    setNum = setNumList.get(inc);
                } catch(Exception e) {
                    Log.d("MinifigsCtrl1","exception from minifigs controller");
                    return;
                }

                Call<MinifigsSets> callLoop = restApi.getMinifigsSetByBricksSetNumByPage(TOKEN_ACCESS_KEY, "application/json", setNum, pageNum);
                callLoop.enqueue(this);

            } else if(nextLink == null) {
                    IFromRestCallback.onGetOnePageResultMinifigsFromRestSuccess(minifigsSets.getResults());

                    try{
                        setNum = setNumList.get(inc);
                        ++inc;
                    } catch(Exception e) {
                        Log.d("MinifigsCtrl2","exception from minifigs controller");
                        throw(e);
                    }

                    int pageNum = 1;
                    Call<MinifigsSets> callLoop = restApi.getMinifigsSetByBricksSetNumByPage(TOKEN_ACCESS_KEY, "application/json", setNum, pageNum);
                    callLoop.enqueue(this);

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

    public MinifigsSingleSet[] getOnePageSetList() {
        return minifigsSets.getResults();
    }

}
