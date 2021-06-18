package com.example.mylego.rest.controllers;

import android.content.Intent;
import android.util.Log;
import com.example.mylego.database.DbSetNumManager;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.PartsSets;
import com.example.mylego.services.RestService;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestOnePageSinglePartsCtrl extends RestCtrl implements Callback<PartsSets> {

    PartsSets partsSets;

    IFromRestCallback IFromRestCallback;

    public static String SET_NUM =  "0011-2";

    DbSetNumManager dbSetNum;
    ArrayList<String> setNumList;

    public RestOnePageSinglePartsCtrl(IFromRestCallback IFromRestCallback) {

        ++RestLimiter.limiter;

        super.start();

        this.IFromRestCallback = IFromRestCallback;

        dbSetNum = new DbSetNumManager(RestService.getContext());
        setNumList = dbSetNum.selectAllQueries();

        partsSets = new PartsSets();

        Call<PartsSets> call = restApi.getPartsSetByBricksSetNum(TOKEN_ACCESS_KEY, "application/json", SET_NUM);
        call.enqueue(this);
    }


    // how fast REST should read data from API
    public static int speed_rest_read = 500;


    // do not change
    public static int counter = 0;
    // do not change
    public static int inc = 1;

    String setNum = "";

    @Override
    public void onResponse(Call<PartsSets> call, Response<PartsSets> response) {

        if(response.isSuccessful()) {

            ++RestLimiter.limiter;

            if(RestLimiter.limiter >= RestLimiter.rest_limit){
                Intent rest = new Intent(RestService.getContext(), RestService.class);
                RestService.getContext().stopService(rest);
                call.cancel();
                return;
            }

            partsSets.setResults(response.body().getResults());


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

                IFromRestCallback.onGetOnePageResultSinglePartsFromRestSuccess(partsSets.getResults());

                String nextPageRaw = nextLink.replaceAll("[^0-9]", "");
                String nextPage = nextPageRaw.substring(1);
                int pageNum = Integer.parseInt(nextPage);

                try{
                    setNum = setNumList.get(inc);
                } catch(Exception e) {
                    Log.d("PartsCtrl1","exception from minifigs controller");
                    return;
                }

                Call<PartsSets> callLoop = restApi.getPartsSetByBricksSetNumByPage(TOKEN_ACCESS_KEY, "application/json", setNum, pageNum);
                callLoop.enqueue(this);



            } else if(nextLink == null && count!=0) {


                IFromRestCallback.onGetOnePageResultSinglePartsFromRestSuccess(partsSets.getResults());

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
            Log.e("REST error", "onResponse method error in PartsCtrl");
            Log.e("error response code", String.valueOf(response.code()));
        }
    }

    @Override
    public void onFailure(Call<PartsSets> call, Throwable t) {
        Log.e("REST error one page","onFailure method error one page read for PartsCtrl");
        t.printStackTrace();
    }
}
