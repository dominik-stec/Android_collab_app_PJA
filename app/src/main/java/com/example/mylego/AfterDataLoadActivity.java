package com.example.mylego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;

import java.util.HashMap;
import java.util.Map;

public class AfterDataLoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_data_load);

        DbManager db = new DbManager(getApplicationContext());

        HashMap<Long, String> queriesStr = db.selectStringQuery(CreateTable.TableEntry.COLUMN_NAME_NAME_STRING, 0, 5);

        for (Map.Entry<Long, String> entry : queriesStr.entrySet()) {
                    Log.d("read query string: ", "id string " + entry.getKey() + " " + entry.getValue());
                    System.out.println("read query as string: " +  "primary key string: " + entry.getKey() + ", column -> name: " + entry.getValue());
                }

        HashMap<Long, Integer> queriesNum = db.selectNumberQuery(CreateTable.TableEntry.COLUMN_NAME_YEAR_INTEGER, 20, 40);

        for (Map.Entry<Long, Integer> entry : queriesNum.entrySet()) {
                    Log.d("read query number: ", "id integer " + entry.getKey() + " " + entry.getValue());
                    System.out.println("read query as number: " +  "primary key number: " + entry.getKey() + ", column -> year: " + entry.getValue());
        }
    }
}