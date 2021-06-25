package com.example.mylego.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.example.mylego.database.DbManager;
import com.example.mylego.database.DbMinifigsManager;
import com.example.mylego.database.DbPartsManager;
import com.example.mylego.database.DbSetNumManager;
import com.example.mylego.database.DbSinglePartsManager;
import com.example.mylego.rest.controllers.RestLimiter;
import com.example.mylego.rest.controllers.RestOnePageBricksCtrl;
import com.example.mylego.rest.controllers.RestOnePageMinifigsCtrl;
import com.example.mylego.rest.controllers.RestOnePagePartsCtrl;
import com.example.mylego.rest.controllers.RestOnePageSinglePartsCtrl;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.domain.MinifigsSingleSet;
import com.example.mylego.rest.domain.Part;
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

                    if (RestOnePageBricksCtrl.counter % 25 == 0) {
                        long progress = Math.round(((double) RestOnePageBricksCtrl.counter / RestOnePageBricksCtrl.to_insert_row_count) * 100);
                        progressBar.putExtra("progressBarVal", progress/2);
                        sendBroadcast(progressBar);
                    }

                    //fill set_num database table
                    dbSetNum.setSetNum(bricksSingleSets.getSet_number());
                    dbSetNum.commitIntoDb();

                }

                // run other rests after load sets data
                if(RestOnePageBricksCtrl.to_insert_row_count==RestOnePageBricksCtrl.counter) {

                    try{
                        Thread.sleep(5000);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }

                    startMinifigsRest();
                    startPartsRest();
                    startSinglePartsRest();
                }

            }

            @Override
            public void onGetOnePageResultMinifigsFromRestSuccess(MinifigsSingleSet[] value) {

            }

            @Override
            public void onGetOnePageResultPartsFromRestSuccess(PartsSingleSet[] value) {

            }

            @Override
            public void onGetOnePageResultSinglePartsFromRestSuccess(PartsSingleSet[] value) {

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

                }

            }

            @Override
            public void onGetOnePageResultPartsFromRestSuccess(PartsSingleSet[] value) {

            }

            @Override
            public void onGetOnePageResultSinglePartsFromRestSuccess(PartsSingleSet[] value) {

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

                int count = value.length;


                for (int i = 0; i < count; i++) {

                    PartsSingleSet partsSingleSet = value[i];

                    db.setId(partsSingleSet.getId());
                    db.setInvPartId(partsSingleSet.getInv_part_id());
                    db.setSetNum(partsSingleSet.getSet_num());
                    db.setQuantity(partsSingleSet.getQuantity());
                    db.setSpare(partsSingleSet.isIs_spare());
                    db.setElementId(partsSingleSet.getElement_id());
                    db.setNumSets(partsSingleSet.getNum_sets());

                    db.commitIntoDb();

                    ++RestOnePagePartsCtrl.counter;

                }

            }

            @Override
            public void onGetOnePageResultSinglePartsFromRestSuccess(PartsSingleSet[] value) {

            }

        });


    }


    public void startSinglePartsRest() {

        new RestOnePageSinglePartsCtrl(new IFromRestCallback() {

            @Override
            public void onGetOnePageResultFromRestSuccess(BricksSingleSet[] value) {

            }

            @Override
            public void onGetOnePageResultMinifigsFromRestSuccess(MinifigsSingleSet[] value) {

            }

            @Override
            public void onGetOnePageResultPartsFromRestSuccess(PartsSingleSet[] value) {

            }

            @Override
            public void onGetOnePageResultSinglePartsFromRestSuccess(PartsSingleSet[] value) {

                context = getApplicationContext();

                DbSinglePartsManager db = new DbSinglePartsManager(getApplicationContext());

                Intent progressBar = new Intent("progressBar");


                int count = value.length;

                for (int i=0; i<count; i++) {

                    Part part = value[i].getPart();

                    db.setId(part.getId());

                    db.setSetNum(value[i].getSet_num());

                    db.setPartNum(part.getPartNum());
                    db.setPartName(part.getPartName());
                    db.setPartCatId(part.getPartCatId());
                    db.setPartUrl(part.getPartUrl());
                    db.setPartImgUrl(part.getPartImgUrl());

                    db.setPartColor(value[i].getColor().getName());

                    db.commitIntoDb();

                    ++RestOnePageSinglePartsCtrl.counter;

                }

                    if (RestLimiter.limiter % 2 == 0) {
                        long progress = Math.round(((double) RestLimiter.limiter / RestLimiter.rest_limit) * 100);
                        progressBar.putExtra("progressBarVal", 50+progress/2);
                        sendBroadcast(progressBar);
                    }

                }

        });


    }



}
