package com.example.mylego.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.example.mylego.database.DbManager;
import com.example.mylego.database.DbMinifigsManager;
import com.example.mylego.database.DbSetNumManager;
import com.example.mylego.rest.controllers.RestOnePageBricksCtrl;
import com.example.mylego.rest.controllers.RestOnePageMinifigsCtrl;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.MinifigsSingleSet;
import java.util.ArrayList;


public class RestService extends IntentService {

    public RestService(){
        super("RestService");
    }

    public static Context context;

    public static Context getContext() {
        return context;
    }

    int count;

    @Override
    protected void onHandleIntent(Intent intent) {

        new RestOnePageBricksCtrl(new IFromRestCallback() {

            @Override
            public void onGetOnePageResultFromRestSuccess(BricksSingleSet[] value) {

                context = getApplicationContext();

                DbManager db = new DbManager(getApplicationContext());

                DbSetNumManager dbSetNum = new DbSetNumManager(getApplicationContext());

                Intent progressBar = new Intent("progressBar");

                count = value.length;

                for (int i = 0; i < count; i++) {

                    BricksSingleSet bricksSingleSets = value[i];

                    db.setSetNumber(bricksSingleSets.getSet_number());
                    db.setName(bricksSingleSets.getName());
                    db.setYear(bricksSingleSets.getYear());
                    db.setThemeId(bricksSingleSets.getTheme_id());
                    db.setNumberOfParts(bricksSingleSets.getNumber_of_parts());
                    db.setImageUrl(bricksSingleSets.getImage_url());
                    db.setSetUrl(bricksSingleSets.getSet_url());
                    db.setModificationDate(bricksSingleSets.getModification_date());

                    db.commitIntoDb();

                    ++RestOnePageBricksCtrl.counter;


                    if (RestOnePageBricksCtrl.counter % 100 == 0) {
                        long progress = Math.round(((double) RestOnePageBricksCtrl.counter / RestOnePageBricksCtrl.to_insert_row_count) * 100);
                        progressBar.putExtra("progressBarVal", progress/2);
                        sendBroadcast(progressBar);
                    }

                    //fill set_num database table
                    dbSetNum.setSetNum(bricksSingleSets.getSet_number());
                    dbSetNum.commitIntoDb();

                }

                // run minifigs rest after load sets data
                if(RestOnePageBricksCtrl.to_insert_row_count==RestOnePageBricksCtrl.counter) {

                    try{
                        Thread.sleep(5000);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }

                    startMinifigsRest();
                }

            }

            @Override
            public void onGetOnePageResultMinifigsFromRestSuccess(MinifigsSingleSet[] value) {

            }

            });






    }

    public void startMinifigsRest() {

        new RestOnePageMinifigsCtrl(new IFromRestCallback() {

            @Override
            public void onGetOnePageResultFromRestSuccess(BricksSingleSet[] value) {

            }

            @Override
            public void onGetOnePageResultMinifigsFromRestSuccess(MinifigsSingleSet[] value) {

                context = getApplicationContext();

                DbMinifigsManager db = new DbMinifigsManager(getApplicationContext());

                Intent progressBar = new Intent("progressBar");

                int count = value.length;


                for (int i = 0; i < count; i++) {

                    DbSetNumManager dbSetNum = new DbSetNumManager(RestService.getContext());
                    ArrayList<String> setNumList = dbSetNum.selectAllQueries();
                    String setNum = setNumList.get(RestOnePageMinifigsCtrl.counter);

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

        });
    }
}
