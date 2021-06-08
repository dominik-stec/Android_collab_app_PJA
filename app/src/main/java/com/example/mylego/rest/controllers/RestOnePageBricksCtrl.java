package com.example.mylego.rest.controllers;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbHelper;
import com.example.mylego.database.DbManager;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.BricksSets;
import com.example.mylego.rest.domain.BricksSingleSet;

import java.util.HashMap;
import java.util.Map;

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

        Call<BricksSets> call = restApi.getSetsByPageNumRest(TOKEN_ACCESS_KEY, "application/json", 1);

        if(isDatabaseEmpty()) {

            call.enqueue(this);

        }
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
    public void onResponse(Call<BricksSets> call, Response<BricksSets> response) {

        if(response.isSuccessful()) {

            bricksSets.setResults(response.body().getResults());

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
                    return;
                }

                Call<BricksSets> callLoop = restApi.getSetsByPageNumRest(TOKEN_ACCESS_KEY, "application/json", pageNum);
                if(isDatabaseEmpty())
                callLoop.enqueue(this);

            } else if(nextLink == null) {
                IFromRestCallback.onGetOnePageResultFromRestSuccess(bricksSets.getResults());
            }


        } else {
            System.out.println(response.errorBody().toString());
            Log.e("REST error", "onResponse method error");
            Log.e("error response code", String.valueOf(response.code()));
        }
    }

    @Override
    public void onFailure(Call<BricksSets> call, Throwable t) {
        Log.e("REST error one page","onFailure method error one page read");
        t.printStackTrace();
    }

    public BricksSingleSet[] getOnePageBricksList() {
        return bricksSets.getResults();
    }

//    public boolean isTableExists(String tableName, boolean openDb) {
//        DbHelper dbHelper = new DbHelper((RestOnePageBricksCtrl)this.getApplicationContext());
//        SQLiteDatabase mDatabase = dbHelper.getReadableDatabase();
//
//        if(openDb) {
//            if(mDatabase == null || !mDatabase.isOpen()) {
//                mDatabase = dbHelper.getReadableDatabase();
//            }
//
//            if(!mDatabase.isReadOnly()) {
//                mDatabase.close();
//                mDatabase = dbHelper.getReadableDatabase();
//            }
//        }
//
//        String query = "select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'";
//        try (Cursor cursor = mDatabase.rawQuery(query, null)) {
//            if(cursor!=null) {
//                if(cursor.getCount()>0) {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }

    public boolean isDatabaseEmpty() {
        DbManager db = new DbManager(this);
        boolean ret = true;

        try {
            HashMap<Long, String> queriesStr = db.selectStringQuery(CreateTable.TableEntry.COLUMN_NAME_NAME_STRING, 0, 1);
            for (Map.Entry<Long, String> entry : queriesStr.entrySet()) {
                if(entry.getKey() >=0 ) ret = false;
            }
        } catch(NullPointerException e) {
            ret = true;
        }
        return ret;
    }

}
