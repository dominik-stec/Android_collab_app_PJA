package com.example.mylego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.mylego.database.DbManager;
import com.example.mylego.rest.controllers.RestAllBricksCtrl;
import com.example.mylego.rest.domain.BricksSets;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.services.RestService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static String bricksSingleSet;
    Intent intentService;


    BroadcastReceiver receiverSetById = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            intentService = intent;
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                bricksSingleSet = bundle.getString(RestService.BRICKS_SINGLE_SET);
                //System.out.println("BRICKS from Activity " + bricksSingleSet);

                Log.i("Android Services", "Exist Intent values in Bundle");
                int resultCode = bundle.getInt(RestService.RESULT_CODE);
                if (resultCode == RESULT_OK) {
                    Log.i("Android Services", "RestService - onSucess method from IFromRestCAllback pass");
                } else {
                    Log.d("Android Services", "RestService - onSucess method from IFromRestCAllback fail");
                }
            }

        }
    };




    BroadcastReceiver receiverSets = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            intentService = intent;
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                BricksSets bricksSet = (BricksSets) bundle.get(RestService.BRICKS_ALL_SETS);
                System.out.println("BRICKS sets from Activity " + bricksSet.getNext());

                Log.i("Android Services sets", "!!!!!!!!!!!!!!!!!Exist Intent values in Bundle");
                int resultCode = bundle.getInt(RestService.RESULT_CODE);
                if (resultCode == RESULT_OK) {
                    Log.i("Android Services sets", "!!!!!!!!!!!!!!!RestService - onSucess method from IFromRestCAllback pass");
                } else {
                    Log.d("Android Services stes", "!!!!!!!!!!!!!!!!!!!RestService - onSucess method from IFromRestCAllback fail");
                }
            }


        }
    };


    BroadcastReceiver receiverAllSets = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            intentService = intent;
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                ArrayList<BricksSingleSet> bricksAllSet = (ArrayList<BricksSingleSet>) bundle.get(RestService.BRICKS_ALL_FULLY_SETS);
                System.out.println("BRICKS fully sets from Activity ");

                for(BricksSingleSet b : bricksAllSet) {
                    System.out.println("!!!!!!name: " + b.getName());
                }

                Log.i("Android Services sets", "!!!!!!!!!!!!!!!!!Exist Intent values in Bundle");
                int resultCode = bundle.getInt(RestService.RESULT_CODE);
                if (resultCode == RESULT_OK) {
                    Log.i("Android Services sets", "!!!!!!!!!!!!!!!RestService - onSucess method from IFromRestCAllback pass");
                } else {
                    Log.d("Android Services stes", "!!!!!!!!!!!!!!!!!!!RestService - onSucess method from IFromRestCAllback fail");
                }
            }


        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(this, RestService.class);

        startService(i);

        System.out.println("BRICKS from Activity 2 " + "");

        System.out.println("!!!!!!!!!!init com.example.mylego.database!!!!!!!!!!!!");

        Intent db = new Intent(this, DatabaseTestActivity.class);
        startActivity(db);


        }





    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(receiverSetById, new IntentFilter(
                RestService.SERVICE_RECEIVER_ONE_SET_ID));

        registerReceiver(receiverSets, new IntentFilter(
                RestService.SERVICE_RECEIVER_ALL_SET_ID));

        registerReceiver(receiverAllSets, new IntentFilter(
                RestService.SERVICE_RECEIVER_ALL_FULLY_SET_ID));


    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiverSetById);
        unregisterReceiver(receiverSets);

    }

}