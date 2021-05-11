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

        while (RestAllBricksCtrl.to_insert_row_count >= DbManager.commit_counter) {
            if (RestAllBricksCtrl.to_insert_row_count == DbManager.commit_counter) {
                HashMap<Long, String> queriesStr = db.selectStringQuery(CreateTable.TableEntry.COLUMN_NAME_NAME, 100, 200);
                for (Map.Entry<Long, String> entry : queriesStr.entrySet()) {
                    Log.d("read query string: ", "id string " + entry.getKey() + " " + entry.getValue());
                }

                HashMap<Long, Integer> queriesNum = db.selectNumberQuery(CreateTable.TableEntry.COLUMN_NAME_YEAR, 0, 100);
                for (Map.Entry<Long, Integer> entry : queriesNum.entrySet()) {
                    Log.d("read query number: ", "id integer " + entry.getKey() + " " + entry.getValue());
                }
                break;
            }

            Log.d("variables test ", "counter value " + Integer.toString(DbManager.commit_counter));
            System.out.println("!!!!!!!!!!init com.example.mylego.database COMPLETE!!!!!!!!!!!!");


        }
    }
}
