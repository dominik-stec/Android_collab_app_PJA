package com.example.mylego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.example.mylego.content_providers.RestContentProvider;

public class RestContentProvidersActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_content_providers);

        onClickAddName();
        onClickRetrieveStudents();
    }
    public void onClickAddName() {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(RestContentProvider.NAME,
                "test_name");

        values.put(RestContentProvider.SET_NUM,
                "test_set_num");

        Uri uri = getContentResolver().insert(
                RestContentProvider.CONTENT_URI, values);

//        Toast.makeText(getBaseContext(),
//                uri.toString(), Toast.LENGTH_LONG).show();
    }
    public void onClickRetrieveStudents() {
        // Retrieve student records
        String URL = "content://com.example.mylego.content_providers.restcontentprovider/bricks";

        Uri bricks = Uri.parse(URL);
        Cursor c = managedQuery(bricks, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                System.out.println(c.getString(c.getColumnIndex(RestContentProvider._ID)) +
                        ", " +  c.getString(c.getColumnIndex( RestContentProvider.NAME)) +
                        ", " + c.getString(c.getColumnIndex( RestContentProvider.SET_NUM)));

            } while (c.moveToNext());
        }
    }
}