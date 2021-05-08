package com.example.mylego.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

//import com.example.mylego.rest.String;
import com.example.mylego.rest.BricksSingleSet;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.RestCtrl;

public class RestService extends IntentService {

    public static final String BRICKS_SINGLE_SET = "BricksSingleSet";
    public static final String RESULT_CODE = "result code from Intent";
    public static final String SERVICE_RECEIVER_ID = "BroadcastReceiver from MainActivity";

    //BricksSingleSet bricksSingleSet;

    public RestService(){
        super("RestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

//        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
//        Bundle bundle = new Bundle();
//
//        BricksSingleSet bricksSingleSet = null;

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
            public void onFailure() {

            }
        }).getById("75954-1");

        //rest.getById("75954-1");

//        java.lang.String apiRequest = intent.getStringExtra(BRICKS_SINGLE_SET);
//        switch(apiRequest){
//            case BRICKS_SINGLE_SET:
//
//                bricksSingleSet = rest.getById("75954-1");
//
//                break;
//        }

//        if (bricksSingleSet != null) {
//            receiver.send(111, bundle);
//        }

        //bundle.putString("111", "test");
        //receiver.send(111, bundle);

//        int result = Activity.RESULT_OK;

//        publishResults(bricksSingleSet.getName(), result);
    }

    private void publishResults(String bricksSingleSet, int result) {
        Intent intent = new Intent(SERVICE_RECEIVER_ID);
        intent.putExtra(BRICKS_SINGLE_SET, bricksSingleSet);
        intent.putExtra(RESULT_CODE, result);
        //startService(intent);
        sendBroadcast(intent);
    }
}
