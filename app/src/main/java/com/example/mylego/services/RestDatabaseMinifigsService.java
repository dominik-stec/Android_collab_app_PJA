package com.example.mylego.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;
import com.example.mylego.database.DbMinifigsManager;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.controllers.RestOnePageBricksCtrl;
import com.example.mylego.rest.controllers.RestOnePageMinifigsCtrl;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.domain.MinifigsSingleSet;

import java.util.HashMap;
import java.util.Map;

public class RestDatabaseMinifigsService extends IntentService {

    public static Context context;

    public static Context getContext() {
        return context;
    }

    public RestDatabaseMinifigsService(){
        super("RestDatabaseMinifigsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        MinifigsSingleSet[] onePageMinifigs = new RestOnePageMinifigsCtrl(new IFromRestCallback() {

            @Override
            public void onGetOnePageResultFromRestSuccess(BricksSingleSet[] value) {

            }

            @Override
            public void onGetOnePageResultMinifigsFromRestSuccess(MinifigsSingleSet[] value) {

                context = getApplicationContext();

                    Log.d("database init", "rest database");

                    DbMinifigsManager db = new DbMinifigsManager(getApplicationContext());

                    Intent progressBar = new Intent("progressBar");

                    int count = value.length;

//                    int loopCounter = 0;
//                    if(!isSetNumsWrite) {
//                        //read set_num for exist bricks set for download minifigs, parts, alternates
//                        HashMap<Long, String> queriesStr = db.selectStringQuery(CreateTable.TableEntry.COLUMN_NAME_SET_NUM_STRING, 0, RestOnePageBricksCtrl.counter);
//                        for (Map.Entry<Long, String> entry : queriesStr.entrySet()) {
//                            setNumsList.add(entry.getValue());
//                            ++loopCounter;
//                        }
//                        if(loopCounter == RestOnePageBricksCtrl.counter) isSetNumsWrite=true;
//                    }

                    for (int i = 0; i < count; i++) {

                        SharedPreferences prefs = getSharedPreferences("shared_preferences", MODE_PRIVATE);
                        String setNum = prefs.getString("setNum", "No name defined");//"No name defined" is the default value.

                        db.setSetNumContain(setNum);

                        MinifigsSingleSet minifigsSingleSets = value[i];

                        db.setId(minifigsSingleSets.getId());
                        db.setSetNum(minifigsSingleSets.getSetNum());
                        db.setSetName(minifigsSingleSets.getSetName());
                        db.setQuantity(minifigsSingleSets.getQuantity());
                        db.setSetImgUrl(minifigsSingleSets.getSetImgUrl());

                        db.commitIntoDb();

                        ++RestOnePageMinifigsCtrl.counter;

                        if (RestOnePageBricksCtrl.counter % 100 == 0) {
                            long progress = Math.round(((double) RestOnePageBricksCtrl.counter / RestOnePageBricksCtrl.to_insert_row_count) * 100);
                            progressBar.putExtra("progressBarVal", 50+progress/3);
                            sendBroadcast(progressBar);
                        }

                    }

            }

        }).getOnePageSetList();


    }

}
