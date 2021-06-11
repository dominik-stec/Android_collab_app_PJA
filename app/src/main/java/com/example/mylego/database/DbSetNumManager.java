package com.example.mylego.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DbSetNumManager {

    DbHelper dbHelper;

    Cursor cursor;

    int id;
    String setNum;

    public DbSetNumManager(Context context) {
        this.dbHelper = new DbHelper(context);

        id = 0;
        setNum = "test num";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetNum() {
        return setNum;
    }

    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }


    public long commitIntoDb() {
        // Gets the data repository in write mode
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CreateTable.TableEntrySetNum.COLUMN_NAME_SETNUM_SET_NUM_STRING, setNum);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = dbWrite.insert(CreateTable.TableEntrySetNum.TABLE_NAME_SETNUM, null, values);

        return newRowId;
    }

    public ArrayList<String> selectAllQueries() {
        ArrayList<String> result = new ArrayList<>();

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                CreateTable.TableEntrySetNum.COLUMN_NAME_SETNUM_SET_NUM_STRING,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntrySetNum.TABLE_NAME_SETNUM,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {

                String stringResult = cursor.getString(cursor.getColumnIndexOrThrow(CreateTable.TableEntrySetNum.COLUMN_NAME_SETNUM_SET_NUM_STRING));
                result.add(stringResult);
            }


        cursor.close();

        return result;
    }


        public HashMap<Long, String> selectStringQuery(String columnType, int startId, int endId) {
        HashMap<Long, String> queryResult = new HashMap<Long, String>();

        switch(columnType) {
            case CreateTable.TableEntrySetNum.COLUMN_NAME_SETNUM_ID:
                throw new IllegalArgumentException("COLUMN_NAME_SETNUM_ID point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
        }

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                columnType,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntrySetNum.TABLE_NAME_SETNUM,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {

            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(CreateTable.TableEntrySetNum._ID));

            if(itemId > endId) break;

            if(itemId >= startId && itemId <= endId) {
                String stringResult = cursor.getString(cursor.getColumnIndexOrThrow(columnType));

                queryResult.put((long)itemId, stringResult);
            }

        }

        cursor.close();
        return queryResult;
    }

    public HashMap<Long, Integer> selectNumberQuery(String columnType, int startId, int endId) {

        HashMap<Long, Integer> queryResult = new HashMap<Long, Integer>();

        switch (columnType) {
            case CreateTable.TableEntrySetNum.COLUMN_NAME_SETNUM_SET_NUM_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_SETNUM_SET_NUM_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
        }

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                columnType,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntrySetNum.TABLE_NAME_SETNUM,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,         // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        while (cursor.moveToNext()) {

            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(CreateTable.TableEntrySetNum._ID));

            if (itemId > endId) break;

            if (itemId >= startId && itemId <= endId) {

                int numberResult = cursor.getInt(cursor.getColumnIndexOrThrow(columnType));

                queryResult.put(itemId, numberResult);
            }

        }

        cursor.close();
        return queryResult;
    }

}
