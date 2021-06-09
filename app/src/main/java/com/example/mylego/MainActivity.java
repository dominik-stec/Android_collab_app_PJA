package com.example.mylego;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mylego.database.DbHelper;
import com.example.mylego.services.RestDatabaseMinifigsService;
import com.example.mylego.services.RestService;


public class MainActivity extends AppCompatActivity {

    Intent intentService;

    public static long progressBar = 0;

    BroadcastReceiver receiverOnePageRestBricks = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            intentService = intent;
            Bundle bundle = intent.getExtras();
            if (bundle != null) {

                progressBar = (long)bundle.get("progressBarVal");
                if(progressBar > 100) progressBar = 100;

                Toast.makeText(getApplicationContext(), "Initilise Database: " + progressBar + " %", Toast.LENGTH_LONG).show();

                if(progressBar == 100) {
//                    Intent basicActivity = new Intent(getApplicationContext(), AfterDataLoadActivity.class);
//                    startActivity(basicActivity);

                    //return to main app activity after init database
                    //Intent returnIntent = new Intent();
                    //returnIntent.putExtra("result", "Database init success");
                    //setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if(!isTableExists("brick_set", false)) {

        Intent i = new Intent(this, RestService.class);
        Intent intentMinifigs = new Intent(this, RestDatabaseMinifigsService.class);
//
//
//        Thread minifigsThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                        startService(intentMinifigs);
//
//            }
//
//        });
//
//        Thread setsThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                startService(i);
//                //minifigsThread.notify();
//
//            }
//        });
//
//        synchronized (setsThread) {
//            setsThread.notifyAll();
//        }
//        synchronized (minifigsThread) {
//            try{
//                minifigsThread.wait();
//            } catch(InterruptedException e) {
//
//            }
//        }
//
//        Thread unlockThreads = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
////                while(progressBar<=50) {
//                    if(progressBar==50) {
//                        //minifigsThread.start();
//                    }
//               // }
//
//            }
//
//        });



//        setsThread.run();
//        minifigsThread.start();

        startService(i);
        startService(intentMinifigs);


        //unlockThreads.start();





//        } else {
//            finish();
//        }


    }

    @Override
    protected void onResume() {
        super.onResume();
            registerReceiver(receiverOnePageRestBricks, new IntentFilter(
                    "progressBar"));




    }

    @Override
    protected void onPause() {
        super.onPause();
            unregisterReceiver(receiverOnePageRestBricks);

    }


}