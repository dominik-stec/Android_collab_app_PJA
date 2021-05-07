package com.example.mylego.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

//import com.example.mylego.rest.String;
import com.example.mylego.rest.BricksSingleSet;
import com.example.mylego.rest.CustomCallback;
import com.example.mylego.rest.RestCtrl;

public class RestService extends IntentService {

    public static final java.lang.String BRICKS_SET_BY_ID = "bricksById";
    public static final java.lang.String RESULT = "result";

    public static final java.lang.String NOTIFICATION = "com.example.mylego.services.RestService.receiver";

    //BricksSingleSet bricksSingleSet;

    public RestService(){
        super("RestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //TODO do something useful

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        Bundle bundle = new Bundle();

        BricksSingleSet bricksSingleSet = null;

        RestCtrl rest = new RestCtrl(new CustomCallback() {
            @Override
            public void onSucess(BricksSingleSet value) {
                System.out.println("name was read " + value.getName());
            }

            @Override
            public void onFailure() {

            }
        });

        java.lang.String apiRequest = intent.getStringExtra(BRICKS_SET_BY_ID);
        switch(apiRequest){
            case BRICKS_SET_BY_ID:

                bricksSingleSet = rest.getById("75954-1");

                break;
        }

//        if (bricksSingleSet != null) {
//            receiver.send(111, bundle);
//        }

        //bundle.putString("111", "test");
        //receiver.send(111, bundle);

        int result = Activity.RESULT_OK;

        publishResults(bricksSingleSet.getName(), result);
    }

    private void publishResults(String bricksSingleSet, int result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(BRICKS_SET_BY_ID, bricksSingleSet);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}
