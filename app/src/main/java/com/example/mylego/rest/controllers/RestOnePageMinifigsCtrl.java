package com.example.mylego.rest.controllers;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbHelper;
import com.example.mylego.database.DbManager;
import com.example.mylego.database.DbSetNumManager;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.MinifigsSets;
import com.example.mylego.rest.domain.MinifigsSingleSet;
import com.example.mylego.services.RestDatabaseMinifigsService;
import com.example.mylego.services.RestService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public static int inc = 1;

    String setNum = "";

    @Override
    public void onResponse(Call<MinifigsSets> call, Response<MinifigsSets> response) {

        if(response.isSuccessful()) {

            //DbSetNumManager dbSetNum = new DbSetNumManager(RestDatabaseMinifigsService.getContext());
            HashMap<Long, String> setNumMap = new HashMap<>();


            minifigsSets.setResults(response.body().getResults());

            String nextLink = response.body().getNext();

            if(nextLink != null) {

                IFromRestCallback.onGetOnePageResultMinifigsFromRestSuccess(minifigsSets.getResults());

                String nextPageRaw = nextLink.replaceAll("[^0-9]", "");
                String nextPage = nextPageRaw.substring(1);
                int pageNum = Integer.parseInt(nextPage);

                //loop iteration
                try{
                    Thread.sleep(speed_rest_read);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

                //pages max
                if(pageNum == counter*100){
                    IFromRestCallback.onGetOnePageResultMinifigsFromRestSuccess(minifigsSets.getResults());
                    return;
                }

                try{
                    //setNumMap = dbSetNum.selectStringQuery(CreateTable.TableEntrySetNum.COLUMN_NAME_SETNUM_SET_NUM_STRING, inc+1, inc+1);
                    setNum = setNumMap.get(1);
                } catch(Exception e) {
                    Log.d("MinifigsCtrl1","exception from minifigs controller");
                    return;
                }


                Call<MinifigsSets> callLoop = restApi.getMinifigsSetByBricksSetNumByPage(TOKEN_ACCESS_KEY, "application/json", setNum, pageNum);
                callLoop.enqueue(this);

            } else if(nextLink == null) {
                    IFromRestCallback.onGetOnePageResultMinifigsFromRestSuccess(minifigsSets.getResults());
                    //SET_NUM = RestService.setNumsList.get(inc);

                    try{
//                        setNumMap = dbSetNum.(CreateTable.TableEntrySetNum.COLUMN_NAME_SETNUM_SET_NUM_STRING, inc, inc+1);
//                        setNum = setNumMap.get(1);

                        DbSetNumManager dbSetNum = new DbSetNumManager(RestService.getContext());
                        HashMap<Long, String> setNameMap = dbSetNum.selectStringQuery(CreateTable.TableEntrySetNum.COLUMN_NAME_SETNUM_SET_NUM_STRING, 0, 3);
                        ArrayList<String> setNumList = dbSetNum.selectAllQueries();
                        String setNum = setNumList.get(inc);
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
