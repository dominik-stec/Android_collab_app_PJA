package com.example.mylego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;

import java.util.HashMap;
import java.util.Map;

public class RestLoadProgressBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_load_progress_bar);

        DbManager db = new DbManager(getApplicationContext());
        HashMap<Long, String> queriesStr = db.selectStringQuery(CreateTable.TableEntry.COLUMN_NAME_NAME, 0, 5);
        for (Map.Entry<Long, String> entry : queriesStr.entrySet()) {
                    Log.d("read query string: ", "id string " + entry.getKey() + " " + entry.getValue());
                }
        //TODO
//        Bundle bundle = getIntent().getExtras();
//        long progress = bundle.getLong("progress");
//        Toast.makeText(this, "initilise database: " + progress, Toast.LENGTH_SHORT).show();
    }
}