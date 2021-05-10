package com.example.mylego.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

//import com.example.mylego.rest.String;
import com.example.mylego.rest.BricksSets;
import com.example.mylego.rest.BricksSingleSet;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.RestAllBricksCtrl;
import com.example.mylego.rest.RestCtrl;

public class RestService extends IntentService {

    public static final String BRICKS_SINGLE_SET = "BricksSingleSet";
    public static final String RESULT_CODE = "result code from Intent";
    public static final String SERVICE_RECEIVER_ID = "BroadcastReceiver from MainActivity";

    public static final String SERVICE_RECEIVER_ALL_SET_ID = "BroadcastReceiver from MainActivity for all sets";
    public static final String BRICKS_ALL_SETS = "BricksSets";

    public RestService(){
        super("RestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        BricksSingleSet bricksSingleSet = new RestCtrl(new IFromRestCallback() {
            @Override
            public void onSucess(BricksSingleSet value) {
                System.out.println("name was read " + value.getName());
                //////////////////////////////////////
                int result = Activity.RESULT_OK;

                publishResults(value.getName(), result);
                //////////////////////////////////////

            }

            @Override
            public void onGetSetRestSucess(BricksSets value) {


            }

            @Override
            public void onFailure() {

            }
        }).getById("75954-1");



        BricksSets bricksSets = new RestAllBricksCtrl(new IFromRestCallback() {
            @Override
            public void onSucess(BricksSingleSet value) {


            }

            @Override
            public void onGetSetRestSucess(BricksSets value) {
                System.out.println("!!!!!!!!!onGetSetRestSucess OK");
                int result = Activity.RESULT_OK;

                Intent intent = new Intent(SERVICE_RECEIVER_ALL_SET_ID);
                intent.putExtra(RESULT_CODE, result);
                intent.putExtra(BRICKS_ALL_SETS, value);
                sendBroadcast(intent);

            }

            @Override
            public void onFailure() {

            }
        }).getById("75954-1");
    }

    private void publishResults(String bricksSingleSet, int result) {
        Intent intent = new Intent(SERVICE_RECEIVER_ID);
        intent.putExtra(BRICKS_SINGLE_SET, bricksSingleSet);
        intent.putExtra(RESULT_CODE, result);
        sendBroadcast(intent);
    }



}
