package com.example.mylego;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;
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
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", "Database init success");
                    setResult(1, "OK", bundle);
                    finish();
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