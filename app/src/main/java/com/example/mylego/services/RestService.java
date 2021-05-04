package com.example.mylego.services;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.mylego.rest.BricksSingleSet;
import com.example.mylego.rest.RestCtrl;

public class RestService extends IntentService {

    public static final String BRICKS_SET_BY_ID = "bricksById";
    public static final String RESULT = "result";

    public static final String NOTIFICATION = "com.example.mylego.services.RestService.receiver";

    //BricksSingleSet bricksSingleSet;

    public RestService(){
        super("RestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //TODO do something useful

        BricksSingleSet bricksSingleSet = null;

        RestCtrl rest = new RestCtrl();

        String apiRequest = intent.getStringExtra(BRICKS_SET_BY_ID);
        switch(apiRequest){
            case BRICKS_SET_BY_ID:

                bricksSingleSet = rest.getById("75954-1");

                break;
        }

        int result = Activity.RESULT_OK;

        publishResults(bricksSingleSet, result);
    }

    private void publishResults(BricksSingleSet bricksSingleSet, int result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(BRICKS_SET_BY_ID, bricksSingleSet);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}
