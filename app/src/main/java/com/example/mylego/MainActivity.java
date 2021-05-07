/**
 * NullPointerException po wczytaniu obiektu(wczytuje się) i próbie jego odczytu(nie odczytuje).
 * Obiekt jest reprezentowany przez
 * BricksSingleSet - wtedy nie powinien zgłaszać null, ale najprawdopodobnie jest to reprezentacja
 * String
 * po wczytaniu danych z REST(odczytuje), spróbować przetwarzać obiekt string reprezentujący JSON-a,
 * z danymi zestawu Lego o podanym ID
 */

package com.example.mylego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

//import com.example.mylego.rest.String;
import com.example.mylego.rest.RestCtrl;

import com.example.mylego.services.RestService;

public class MainActivity extends AppCompatActivity {

    RestCtrl rest;
    String bricksSingleSet;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                bricksSingleSet = bundle.getString(RestService.BRICKS_SET_BY_ID);
                int resultCode = bundle.getInt(RestService.RESULT);
                if (resultCode == RESULT_OK) {
                    Log.d("service", "Download pass");
                    //System.out.println("name set: " + bricksSingleSet.getName());
                    System.out.println("name set: \n" + bricksSingleSet);
                } else {
                    Log.d("service", "Download failed");
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        rest = new RestCtrl();
//        bricksSingleSet = rest.getById("75954-1");


        ////////////////////////////////////////////////////////////
        // use this to start and trigger a service
        Intent i = new Intent(this, RestService.class);
        i.putExtra(RestService.BRICKS_SET_BY_ID, RestService.BRICKS_SET_BY_ID);
        // potentially add data to the intent
//        i.putExtra("KEY1", "Value to be used by the service");
        startService(i);
///////////////////////////////////////////////////////////////////////
        //bricksSingleSet.getName();


        //rest.getSetById("75954-1");
        //Log.d("error", "String result: \n" + bricksSingleSet.getName());

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(
                RestService.NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

}