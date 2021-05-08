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

import com.example.mylego.services.RestService;

public class MainActivity extends AppCompatActivity {

    String bricksSingleSet;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                bricksSingleSet = bundle.getString(RestService.BRICKS_SINGLE_SET);
                System.out.println("BRICKS " + bricksSingleSet);
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

        Intent i = new Intent(this, RestService.class);
        i.putExtra(RestService.BRICKS_SINGLE_SET, RestService.BRICKS_SINGLE_SET);

        startService(i);

        //System.out.println("BRICKS " + bricksSingleSet);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(
                RestService.SERVICE_RECEIVER_ID));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

}