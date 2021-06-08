package com.example.mylego.services;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mylego.database.DbHelper;
import com.example.mylego.database.DbManager;
import com.example.mylego.rest.controllers.RestOnePageBricksCtrl;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.MinigfigsSingleSet;


public class RestService extends IntentService {

    public static final String BRICKS_SINGLE_SET = "BricksSingleSet";
    public static final String RESULT_CODE = "result code from Intent";
    public static final String BRICKS_ALL_SETS = "BricksSets";

    public RestService(){
        super("RestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        BricksSingleSet[] onePageBricks = new RestOnePageBricksCtrl(new IFromRestCallback() {

            @Override
            public void onGetOnePageResultFromRestSuccess(BricksSingleSet[] value) {


                DbManager db = new DbManager(getApplicationContext());

                Intent progressBar = new Intent("progressBar");

                int count = value.length;

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
                        progressBar.putExtra("progressBarVal", progress);
                        sendBroadcast(progressBar);
                    }

                }

            }

            @Override
            public void onGetOnePageResultFromRestSuccess(MinigfigsSingleSet[] value) {

            }


        }).getOnePageBricksList();

    }
}
