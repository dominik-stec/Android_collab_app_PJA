package com.example.mylego.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.mylego.DatabaseTestActivity;
import com.example.mylego.RestLoadProgressBar;
import com.example.mylego.database.DbManager;
import com.example.mylego.rest.domain.BricksSets;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.controllers.RestAllBricksCtrl;
import com.example.mylego.rest.controllers.RestBricksByIdCtrl;

import java.util.ArrayList;
import java.util.List;

public class RestService extends IntentService {

    public static final String BRICKS_SINGLE_SET = "BricksSingleSet";
    public static final String RESULT_CODE = "result code from Intent";
    public static final String SERVICE_RECEIVER_ONE_SET_ID = "BroadcastReceiver from MainActivity for sets by id";

    public static final String SERVICE_RECEIVER_ALL_SET_ID = "BroadcastReceiver from MainActivity for all sets";
    public static final String BRICKS_ALL_SETS = "BricksSets";

    public static final String SERVICE_RECEIVER_ALL_FULLY_SET_ID = "BroadcastReceiver from MainActivity for all fully sets";
    public static final String BRICKS_ALL_FULLY_SETS = "List<BricksSets>";


    public RestService(){
        super("RestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        BricksSingleSet bricksSingleSet = new RestBricksByIdCtrl(new IFromRestCallback() {
            @Override
            public void onGetSetByIdRestSuccess(BricksSingleSet value) {
                System.out.println("name was read " + value.getName());
                int result = Activity.RESULT_OK;

                publishResultsForSetById(value.getName(), result);

            }

            @Override
            public void onGetSetsRestSuccess(BricksSets value) {


            }

            @Override
            public void onGetSetsRestAllSuccess(List<BricksSingleSet[]> value) {

            }

            @Override
            public void onFailure() {

            }
        }).getById("75954-1");



        BricksSets bricksSets = new RestAllBricksCtrl(new IFromRestCallback() {
            @Override
            public void onGetSetByIdRestSuccess(BricksSingleSet value) {


            }

            @Override
            public void onGetSetsRestSuccess(BricksSets value) {
                System.out.println("!!!!!!!!!onGetSetRestSucess OK");
                int result = Activity.RESULT_OK;

                Log.d("bricks result from list", value.getResults()[99].getName());

                publishResultsForAllSets(value, result);

//                Intent intent = new Intent(SERVICE_RECEIVER_ALL_SET_ID);
//                intent.putExtra(RESULT_CODE, result);
//                intent.putExtra(BRICKS_ALL_SETS, value);
//                sendBroadcast(intent);

            }

            @Override
            public void onGetSetsRestAllSuccess(List<BricksSingleSet[]> value) {

            }

            @Override
            public void onFailure() {

            }
        }).getSets();
    }

    List<BricksSingleSet[]> bricksAll = new RestAllBricksCtrl(new IFromRestCallback() {
        @Override
        public void onGetSetByIdRestSuccess(BricksSingleSet value) {


        }

        @Override
        public void onGetSetsRestSuccess(BricksSets value) {


        }

        @Override
        public void onGetSetsRestAllSuccess(List<BricksSingleSet[]> value) {
            //TODO disable value to BricksSet and to db
            ///////////////////////////////
            ArrayList<BricksSingleSet> allBricksList = new ArrayList<BricksSingleSet>();
            for(BricksSingleSet[] partialBricksList : value) {
                int length = partialBricksList.length;
                for(int i=0; i<length; i++) {
                    allBricksList.add(partialBricksList[i]);
                }
            }
            Log.d("database insert", "number of items in array: " + allBricksList.size());

            //SyncRestDb insertCount = (SyncRestDb) getApplicationContext();

            int counter = 0;
            //SyncRestDb syncRestDb = (SyncRestDb) getApplicationContext();
            Intent intent = new Intent(getApplicationContext(), DatabaseTestActivity.class);
            Intent progressBar = new Intent(SERVICE_RECEIVER_ALL_FULLY_SET_ID);

            DbManager db = new DbManager(getApplicationContext());
            for(BricksSingleSet bricks : allBricksList) {
                db.setSetNumber(bricks.getSetNum());
                db.setName(bricks.getName());
                db.setYear(bricks.getYear());
                db.setThemeId(bricks.getThemeId());
                db.setNumberOfParts(bricks.getNumParts());
                db.setImageUrl(bricks.getSetImgUrl());
                db.setSetUrl(bricks.getSetUrl());
                db.setModificationDate(bricks.getLastModifiedDt());

                db.commitIntoDb();
                Log.d("database insert", "from callback insert");

                ++counter;

                if(counter % 100 == 0) {
                    Log.d("database insert", "from callback insert with modulo 100 count");
                    long progress = Math.round(((double)counter/RestAllBricksCtrl.to_insert_row_count)*100);
                    progressBar.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    progressBar.putExtra("progress", progress);
                    sendBroadcast(progressBar);
                    //startActivity(progressBar);
                }

                //intent.putExtra("counter", ++counter);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                //insertCount.getInstance().setData(++counter);
                //((SyncRestDb) getApplication()).setData(100);
            }
            //TODO down uncomment
////////////////////////////////////
            if(counter == RestAllBricksCtrl.to_insert_row_count) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("counter", counter);
                startActivity(intent);
            }
            //db.readFromDb();
            //publishResultsForAllFullySets(allBricksList.get(10).getName(), Activity.RESULT_OK);
        }

        @Override
        public void onFailure() {

        }

    }).getAllSets();


    private void publishResultsForSetById(String bricksSingleSet, int result) {
        Intent intent = new Intent(SERVICE_RECEIVER_ONE_SET_ID);
        intent.putExtra(BRICKS_SINGLE_SET, bricksSingleSet);
        intent.putExtra(RESULT_CODE, result);
        sendBroadcast(intent);
    }

    private void publishResultsForAllSets(BricksSets bricksSets, int result) {
        Intent intent = new Intent(SERVICE_RECEIVER_ALL_SET_ID);
        intent.putExtra(RESULT_CODE, result);
        intent.putExtra(BRICKS_ALL_SETS, bricksSets);
        sendBroadcast(intent);
    }

    private void publishResultsForAllFullySets(String testName, int result) {
        Intent intent = new Intent(SERVICE_RECEIVER_ALL_FULLY_SET_ID);
        intent.putExtra(BRICKS_ALL_FULLY_SETS, testName);
        intent.putExtra(RESULT_CODE, result);
        sendBroadcast(intent);
    }



}
