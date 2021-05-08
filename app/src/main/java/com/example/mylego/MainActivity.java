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

import com.example.mylego.content_providers.RestContentProvider;
import com.example.mylego.services.RestService;

public class MainActivity extends AppCompatActivity {

    static String bricksSingleSet;
    Intent intentService;
//    Intent i;

//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Bundle bundle = intent.getExtras();
//            if (bundle != null) {
//                bricksSingleSet = bundle.getString(RestService.BRICKS_SINGLE_SET);
//                System.out.println("BRICKS " + bricksSingleSet);
//
//                Log.i("Android Services", "Exist Intent values in Bundle");
//                int resultCode = bundle.getInt(RestService.RESULT_CODE);
//                if (resultCode == RESULT_OK) {
//                    Log.i("Android Services", "RestService - onSucess method from IFromRestCAllback pass");
//                } else {
//                    Log.d("Android Services", "RestService - onSucess method from IFromRestCAllback fail");
//                }
//            }
//        }
//    };

    BroadcastReceiver receiver = new BroadcastReceiver() {

        //final MainActivity act = MainActivity.this;

        @Override
        public void onReceive(Context context, Intent intent) {
            intentService = intent;
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                bricksSingleSet = bundle.getString(RestService.BRICKS_SINGLE_SET);
                System.out.println("BRICKS from Activity " + bricksSingleSet);

//                    i = new Intent(context, RestService.class);
//                    i.putExtra("Hogwart", bricksSingleSet);

                //startService(i);
//
//                    Intent i = new Intent(context, RestService.class);
//                    startService(i);
//                    registerReceiver(this, new IntentFilter(
//                            RestService.SERVICE_RECEIVER_ID));

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String bricksSingleSet;



        Intent i = new Intent(this, RestService.class);
//        //i.putExtra(RestService.BRICKS_SINGLE_SET, RestService.BRICKS_SINGLE_SET);
//
        startService(i);

        System.out.println("BRICKS from Activity 2 " + "");

        Intent providerIntent = new Intent(this, MainActivity2.class);
        startActivity(providerIntent);

//
////        System.out.println("BRICKS activity " + bricksSingleSet);
////        System.out.println("BRICKS activity receiver " + receiver);
////////////////////////////////////////////////////////////////////////
//        registerReceiver(receiver, new IntentFilter(
//                RestService.SERVICE_RECEIVER_ID));
        //////////////////////////////////////////////////////////////////
        //System.out.println("BRICKS from Activity resume " + bricksSingleSet);

        //System.out.println("BRICKS from Activity resume " + receiver.getResultExtras(false).getString(RestService.BRICKS_SINGLE_SET));
    }

    @Override
    protected void onResume() {
        super.onResume();
        //System.out.println("BRICKS from Activity resume " + bricksSingleSet);

        registerReceiver(receiver, new IntentFilter(
                RestService.SERVICE_RECEIVER_ID));
    }
//
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

}