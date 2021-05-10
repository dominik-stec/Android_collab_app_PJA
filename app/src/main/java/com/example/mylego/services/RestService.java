package com.example.mylego.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;

import com.example.mylego.rest.domain.BricksSets;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.controllers.RestAllBricksCtrl;
import com.example.mylego.rest.controllers.RestBricksByIdCtrl;

public class RestService extends IntentService {

    public static final String BRICKS_SINGLE_SET = "BricksSingleSet";
    public static final String RESULT_CODE = "result code from Intent";
    public static final String SERVICE_RECEIVER_ONE_SET_ID = "BroadcastReceiver from MainActivity for sets by id";

    public static final String SERVICE_RECEIVER_ALL_SET_ID = "BroadcastReceiver from MainActivity for all sets";
    public static final String BRICKS_ALL_SETS = "BricksSets";

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

                publishResultsForAllSets(value, result);

//                Intent intent = new Intent(SERVICE_RECEIVER_ALL_SET_ID);
//                intent.putExtra(RESULT_CODE, result);
//                intent.putExtra(BRICKS_ALL_SETS, value);
//                sendBroadcast(intent);

            }

            @Override
            public void onFailure() {

            }
        }).getSets();
    }

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



}
