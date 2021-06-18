package com.example.mylego;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;
import com.example.mylego.services.RestService;


public class MainActivity extends AppCompatActivity {

    Intent rest;
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

//                if(progressBar == 100) {
//                    finish();
//                }

                // should be progressBar==100 after all rest read implements
                if(progressBar == 100) {
                    finish();
                }

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rest = new Intent(this, RestService.class);

        startService(rest);

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