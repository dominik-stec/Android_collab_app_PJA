package com.example.mylego.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.example.mylego.database.DbManager;
import com.example.mylego.database.DbMinifigsManager;
import com.example.mylego.database.DbPartsManager;
import com.example.mylego.database.DbSetNumManager;
import com.example.mylego.rest.controllers.RestOnePageBricksCtrl;
import com.example.mylego.rest.controllers.RestOnePageMinifigsCtrl;
import com.example.mylego.rest.controllers.RestOnePagePartsCtrl;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.MinifigsSingleSet;
import com.example.mylego.rest.domain.PartsSingleSet;

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

                    db.setSetNumber(bricksSingleSets.getSetNum());
                    db.setName(bricksSingleSets.getName());
                    db.setYear(bricksSingleSets.getYear());
                    db.setThemeId(bricksSingleSets.getThemeId());
                    db.setNumberOfParts(bricksSingleSets.getNumParts());
                    db.setImageUrl(bricksSingleSets.getSetImgUrl());
                    db.setSetUrl(bricksSingleSets.getSetUrl());
                    db.setModificationDate(bricksSingleSets.getLastModifiedDt());

                    db.commitIntoDb();

                    ++RestOnePageBricksCtrl.counter;


                    if (RestOnePageBricksCtrl.counter % 100 == 0) {
                        long progress = Math.round(((double) RestOnePageBricksCtrl.counter / RestOnePageBricksCtrl.to_insert_row_count) * 100);
                        progressBar.putExtra("progressBarVal", progress/2);
                        sendBroadcast(progressBar);
                    }

                    //fill set_num database table
                    dbSetNum.setSetNum(bricksSingleSets.getSetNum());
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
                    startPartsRest();
                }

            }

            @Override
            public void onGetOnePageResultMinifigsFromRestSuccess(MinifigsSingleSet[] value) {

            }

            @Override
            public void onGetOnePageResultPartsFromRestSuccess(PartsSingleSet[] value) {

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

            @Override
            public void onGetOnePageResultPartsFromRestSuccess(PartsSingleSet[] value) {

            }

        });
    }

    public void startPartsRest() {

        new RestOnePagePartsCtrl(new IFromRestCallback() {

            @Override
            public void onGetOnePageResultFromRestSuccess(BricksSingleSet[] value) {

            }

            @Override
            public void onGetOnePageResultMinifigsFromRestSuccess(MinifigsSingleSet[] value) {

            }

            @Override
            public void onGetOnePageResultPartsFromRestSuccess(PartsSingleSet[] value) {

                context = getApplicationContext();

                DbPartsManager db = new DbPartsManager(getApplicationContext());

                Intent progressBar = new Intent("progressBar");

                int count = value.length;


                for (int i = 0; i < count; i++) {

//                    DbSetNumManager dbSetNum = new DbSetNumManager(RestService.getContext());
//                    ArrayList<String> setNumList = dbSetNum.selectAllQueries();
//                    String setNum = setNumList.get(RestOnePagePartsCtrl.counter);
//
//                    db.setSetNum(setNum);

                    PartsSingleSet partsSingleSet = value[i];

                    db.setId(partsSingleSet.getId());
                    db.setInvPartId(partsSingleSet.getInvPartId());
                    db.setSetNum(partsSingleSet.getSetNum());
                    db.setSetNum(partsSingleSet.getSetNum());
                    db.setQuantity(partsSingleSet.getQuantity());
                    db.setSpare(partsSingleSet.isSpare());
                    db.setElementId(partsSingleSet.getElementId());
                    db.setNumSets(partsSingleSet.getNumSets());

                    db.commitIntoDb();

                    ++RestOnePagePartsCtrl.counter;

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
