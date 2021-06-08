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
        setImgUrl = "test set img url";
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
        values.put(CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_ID_INTEGER, id);
        values.put(CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_NUM_STRING, setNum);
        values.put(CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_NAME_STRING, setName);
        values.put(CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_QUANTITY_INTEGER, quantity);
        values.put(CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_IMG_URL_STRING, setImgUrl);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = dbWrite.insert(CreateTable.TableEntryMinifigs.TABLE_NAME_MINIFIG, null, values);

        return newRowId;
    }

    public HashMap<Long, String> selectStringQuery(String columnType, int startId, int endId) {
        HashMap<Long, String> queryResult = new HashMap<Long, String>();

        switch(columnType) {
            case CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_ID_INTEGER:
                throw new IllegalArgumentException("COLUMN_NAME_MINIFIG_ID_INTEGER point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
            case CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_QUANTITY_INTEGER:
                throw new IllegalArgumentException("COLUMN_NAME_MINIFIG_QUANTITY_INTEGER point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
        }

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                columnType,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntryMinifigs.TABLE_NAME_MINIFIG,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {

            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(CreateTable.TableEntryMinifigs._ID));

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
            case CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_NUM_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_MINIFIG_SET_NUM_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_NAME_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_MINIFIG_SET_NAME_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_IMG_URL_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_MINIFIG_SET_IMG_URL_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            }

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                columnType,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntryMinifigs.TABLE_NAME_MINIFIG,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,         // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        while (cursor.moveToNext()) {

            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(CreateTable.TableEntryMinifigs._ID));

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
