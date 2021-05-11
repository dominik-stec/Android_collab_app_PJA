package com.example.mylego;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;
import com.example.mylego.rest.controllers.RestAllBricksCtrl;

import java.util.HashMap;
import java.util.Map;

public class DatabaseTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);

        DbManager db = new DbManager(this);
//        db.setName("test name for insert");
//        db.writeIntoDb();
        Log.d("init db in activity: ", "activity run");

        Bundle bundle = getIntent().getExtras();
        int counter = bundle.getInt("counter");
        Log.d("success", "program run successful " + counter);
        //SyncRestDb sync = (SyncRestDb) getApplicationContext();
//        int counter = 0;
//        while(counter != 1000) {
//            Bundle b = getIntent().getExtras();
//            counter = b.getInt("counter");
//            //counter = ((SyncRestDb) getApplication()).getData();
//            try{
//                Thread.sleep(5000);
//            }catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Log.d("counter state: ", "counter state " + counter);
//        }
////////////////////////////
//        while (1000 >= SyncRestDb.insert_counter) {
//            if (1000 == SyncRestDb.insert_counter) {
//                HashMap<Long, String> queriesStr = db.selectStringQuery(CreateTable.TableEntry.COLUMN_NAME_NAME, 100, 200);
//                for (Map.Entry<Long, String> entry : queriesStr.entrySet()) {
//                    Log.d("read query string: ", "id string " + entry.getKey() + " " + entry.getValue());
//                }
//
//                HashMap<Long, Integer> queriesNum = db.selectNumberQuery(CreateTable.TableEntry.COLUMN_NAME_YEAR, 0, 100);
//                for (Map.Entry<Long, Integer> entry : queriesNum.entrySet()) {
//                    Log.d("read query number: ", "id integer " + entry.getKey() + " " + entry.getValue());
//                }
//                break;
//            }
//
//            Log.d("variables test ", "counter value " + Integer.toString(SyncRestDb.insert_counter));
///////////////////////////
            System.out.println("!!!!!!!!!!init com.example.mylego.database COMPLETE!!!!!!!!!!!!");


        }
    }

