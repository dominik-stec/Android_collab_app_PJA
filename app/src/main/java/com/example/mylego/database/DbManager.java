package com.example.mylego.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.mylego.rest.domain.BricksSingleSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbManager {

    public DbManager(Context context) {
        this.dbHelper = new DbHelper(context);

        setNumber = "test set number";
        name = "test name";
        year = 1999;
        themeId = 2000;
        numberOfParts = 3000;
        imageUrl = "test image url";
        setUrl = "test url of set";
        modificationDate = "test modification date";
    }

    DbHelper dbHelper;

    Cursor cursor;

    String setNumber;
    String name;
    int year;
    int themeId;
    int numberOfParts;
    String imageUrl;
    String setUrl;
    String modificationDate;

    public String getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getNumberOfParts() {
        return numberOfParts;
    }

    public void setNumberOfParts(int numberOfParts) {
        this.numberOfParts = numberOfParts;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSetUrl() {
        return setUrl;
    }

    public void setSetUrl(String setUrl) {
        this.setUrl = setUrl;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
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



    //
    //// for future use
    //
//    public void readFromDb() {
//
//        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
//
//// Define a projection that specifies which columns from the com.example.mylego.database
//// you will actually use after this query.
//        String[] projection = {
//                BaseColumns._ID,
//                CreateTable.TableEntry.COLUMN_NAME_SET_NUM,
//                CreateTable.TableEntry.COLUMN_NAME_NAME,
//                CreateTable.TableEntry.COLUMN_NAME_YEAR,
//                CreateTable.TableEntry.COLUMN_NAME_THEME_ID,
//                CreateTable.TableEntry.COLUMN_NAME_NUM_PARTS,
//                CreateTable.TableEntry.COLUMN_NAME_SET_IMG_URL,
//                CreateTable.TableEntry.COLUMN_NAME_SET_URL,
//                CreateTable.TableEntry.COLUMN_NAME_LAST_MODIFIED_DT,
//        };
//
//// Filter results WHERE "title" = 'My Title'
//        String selection = CreateTable.TableEntry.COLUMN_NAME_NAME + " = ?";
//        String[] selectionArgs = { "test name" };
//
//// How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                CreateTable.TableEntry._ID + " ASC";
//
//        this.cursor = dbRead.query(
//                CreateTable.TableEntry.TABLE_NAME,   // The table to query
//                projection,             // The array of columns to return (pass null to get all)
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
//                null,                   // don't group the rows
//                null,                   // don't filter by row groups
//                sortOrder               // The sort order
//        );
//
//        List<Long> itemIds = new ArrayList<Long>();
//        List<String> namesList = new ArrayList<String>();
//        while(cursor.moveToNext()) {
//            long itemId = cursor.getLong(
//                    cursor.getColumnIndexOrThrow(CreateTable.TableEntry._ID));
//            String name = cursor.getString(cursor.getColumnIndexOrThrow(CreateTable.TableEntry.COLUMN_NAME_NAME));
//            itemIds.add(itemId);
//            namesList.add(name);
//        }
//        cursor.close();
//
//        for(long i : itemIds) {
//            Log.d("TEST BRICKS DB LIST", itemIds.toString());
//            System.out.println("testing SQL" + i);
//        }
//
//        for(String i : namesList) {
//            System.out.println("testing SQL result for name :  " + i);
//            Log.d("TEST BRICKS name:  ", i);
//        }
//    }

}




