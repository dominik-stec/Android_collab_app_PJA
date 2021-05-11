package com.example.mylego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class RestLoadProgressBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_load_progress_bar);
        Bundle bundle = getIntent().getExtras();
        long progress = bundle.getLong("progress");
        Toast.makeText(this, "initilise database: " + progress, Toast.LENGTH_SHORT).show();
    }
}