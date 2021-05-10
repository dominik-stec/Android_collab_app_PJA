package com.example.mylego.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
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

    public DbHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }


    public void writeIntoDb() {
        // Gets the data repository in write mode
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();

        String setNumber = "test set number";
        String name = "test name";
        int year = 1999;
        int themeId = 2000;
        int numberOfParts = 3000;
        String imageUrl = "test image url";
        String setUrl = "test url of set";
        String modificationDate = "test modification date";


// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CreateTable.TableEntry.COLUMN_NAME_SET_NUM, setNumber);
        values.put(CreateTable.TableEntry.COLUMN_NAME_NAME, name);
        values.put(CreateTable.TableEntry.COLUMN_NAME_YEAR, year);
        values.put(CreateTable.TableEntry.COLUMN_NAME_THEME_ID, themeId);
        values.put(CreateTable.TableEntry.COLUMN_NAME_NUM_PARTS, numberOfParts);
        values.put(CreateTable.TableEntry.COLUMN_NAME_SET_IMG_URL, imageUrl);
        values.put(CreateTable.TableEntry.COLUMN_NAME_SET_URL, setUrl);
        values.put(CreateTable.TableEntry.COLUMN_NAME_LAST_MODIFIED_DT, modificationDate);


// Insert the new row, returning the primary key value of the new row
        long newRowId = dbWrite.insert(CreateTable.TableEntry.TABLE_NAME, null, values);

    }

    public void ReadFromDb() {

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the com.example.mylego.database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                CreateTable.TableEntry.COLUMN_NAME_SET_NUM,
                CreateTable.TableEntry.COLUMN_NAME_NAME,
                CreateTable.TableEntry.COLUMN_NAME_YEAR,
                CreateTable.TableEntry.COLUMN_NAME_THEME_ID,
                CreateTable.TableEntry.COLUMN_NAME_NUM_PARTS,
                CreateTable.TableEntry.COLUMN_NAME_SET_IMG_URL,
                CreateTable.TableEntry.COLUMN_NAME_SET_URL,
                CreateTable.TableEntry.COLUMN_NAME_LAST_MODIFIED_DT,
        };

// Filter results WHERE "title" = 'My Title'
        String selection = CreateTable.TableEntry.COLUMN_NAME_NAME + " = ?";
        String[] selectionArgs = { "test name" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                CreateTable.TableEntry.COLUMN_NAME_NAME + " DESC";

        Cursor cursor = dbRead.query(
                CreateTable.TableEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<Long> itemIds = new ArrayList<Long>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(CreateTable.TableEntry._ID));
            itemIds.add(itemId);
        }
        cursor.close();

        for(long i : itemIds) {
            Log.d("TEST BRICKS DB LIST", itemIds.toString());
            System.out.println("testing SQL" + i);
        }
    }

}




