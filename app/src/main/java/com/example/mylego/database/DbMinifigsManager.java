package com.example.mylego.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.HashMap;

public class DbMinifigsManager {

    DbHelper dbHelper;

    Cursor cursor;

    int id;
    String setNum;
    String setName;
    int quantity;
    String setImgUrl;

    public DbMinifigsManager(Context context) {
        this.dbHelper = new DbHelper(context);

        id = 0;
        setNum = "test set num";
        setName = "test set name";
        quantity = 1;
        setImgUrl = "test et img url";
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

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSetImgUrl() {
        return setImgUrl;
    }

    public void setSetImgUrl(String setImgUrl) {
        this.setImgUrl = setImgUrl;
    }

    public long commitIntoDb() {
        // Gets the data repository in write mode
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CreateTable.TableEntry.COLUMN_NAME_SET_NUM_STRING, setNumber);
        values.put(CreateTable.TableEntry.COLUMN_NAME_NAME_STRING, name);
        values.put(CreateTable.TableEntry.COLUMN_NAME_YEAR_INTEGER, year);
        values.put(CreateTable.TableEntry.COLUMN_NAME_THEME_ID_INTEGER, themeId);
        values.put(CreateTable.TableEntry.COLUMN_NAME_NUM_PARTS_INTEGER, numberOfParts);
        values.put(CreateTable.TableEntry.COLUMN_NAME_SET_IMG_URL_STRING, imageUrl);
        values.put(CreateTable.TableEntry.COLUMN_NAME_SET_URL_STRING, setUrl);
        values.put(CreateTable.TableEntry.COLUMN_NAME_LAST_MODIFIED_DT_STRING, modificationDate);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = dbWrite.insert(CreateTable.TableEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    public HashMap<Long, String> selectStringQuery(String columnType, int startId, int endId) {
        HashMap<Long, String> queryResult = new HashMap<Long, String>();

        switch(columnType) {
            case CreateTable.TableEntry.COLUMN_NAME_YEAR_INTEGER:
                throw new IllegalArgumentException("COLUMN_NAME_YEAR point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
            case CreateTable.TableEntry.COLUMN_NAME_THEME_ID_INTEGER:
                throw new IllegalArgumentException("COLUMN_NAME_THEME_ID point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
            case CreateTable.TableEntry.COLUMN_NAME_NUM_PARTS_INTEGER:
                throw new IllegalArgumentException("COLUMN_NAME_NUM_PARTS point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
        }

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                columnType,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {

            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(CreateTable.TableEntry._ID));

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
            case CreateTable.TableEntry.COLUMN_NAME_SET_NUM_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_YEAR point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntry.COLUMN_NAME_NAME_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_NAME point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntry.COLUMN_NAME_SET_IMG_URL_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_SET_IMG_URL point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntry.COLUMN_NAME_SET_URL_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_SET_URL point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntry.COLUMN_NAME_LAST_MODIFIED_DT_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_LAST_MODIFIED_DT point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
        }

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                columnType,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,         // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        while (cursor.moveToNext()) {

            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(CreateTable.TableEntry._ID));

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
