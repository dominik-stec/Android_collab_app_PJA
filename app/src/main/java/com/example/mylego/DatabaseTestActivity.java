package com.example.mylego;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylego.database.DbManager;

public class DatabaseTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);

        DbManager db = new DbManager(this);
        db.writeIntoDb();
        db.ReadFromDb();
        System.out.println("!!!!!!!!!!init com.example.mylego.database COMPLETE!!!!!!!!!!!!");


    }
}
