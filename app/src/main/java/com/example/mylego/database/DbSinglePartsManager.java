package com.example.mylego.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.example.mylego.rest.domain.Part;
import java.util.ArrayList;
import java.util.HashMap;

public class DbSinglePartsManager {

    DbHelper dbHelper = null;

    Integer id;
    String setNum;
    String partNum;
    String partName;
    Integer partCatId;
    String partUrl;
    String partImgUrl;
    String partColor;

    public DbSinglePartsManager(Context context) {
        this.dbHelper = new DbHelper(context);

         id = 0;
         setNum = "test";
         partNum = "test";
         partName = "test";
         partCatId = 0;
         partUrl = "test";
         partImgUrl = "test";
         partColor = "test";

    }



    public long commitIntoDb() {
        // Gets the data repository in write mode
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CreateTable.TableEntrySinglePart._ID, id);
        values.put(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_SET_NUM_STRING, setNum);
        values.put(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_NUM_STRING, partNum);
        values.put(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_NAME_STRING, partName);
        values.put(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_CAT_ID_INTEGER, partCatId);
        values.put(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_URL_STRING, partUrl);
        values.put(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_IMG_URL_STRING, partImgUrl);
        values.put(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_COLOR_STRING, partColor);


        // Insert the new row, returning the primary key value of the new row
        long newRowId = dbWrite.insert(CreateTable.TableEntrySinglePart.COLUMN_NAME_SINGLE_PARTS, null, values);

        return newRowId;
    }

    public ArrayList<Part> getSinglePartsBySetNum(String setNum) {

        ArrayList<Part> partsList = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                CreateTable.TableEntrySinglePart._ID,
                CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_SET_NUM_STRING,
                CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_NUM_STRING,
                CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_NAME_STRING,
                CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_CAT_ID_INTEGER,
                CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_URL_STRING,
                CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_IMG_URL_STRING,
                CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_COLOR_STRING
        };

        String selection = CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_SET_NUM_STRING + "=?";

        String[] selectionArgs = {setNum};

        Cursor cursor = db.query(CreateTable.TableEntrySinglePart.COLUMN_NAME_SINGLE_PARTS, projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {

            do {
                Part part = new Part();

                part.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntrySinglePart._ID)));
                part.setSetNum(setNum);
                part.setPartNum(cursor.getString(cursor.getColumnIndexOrThrow(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_NUM_STRING)));
                part.setPartName(cursor.getString(cursor.getColumnIndexOrThrow(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_NAME_STRING)));
                part.setPartCatId(cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_CAT_ID_INTEGER)));
                part.setPartUrl(cursor.getString(cursor.getColumnIndexOrThrow(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_URL_STRING)));
                part.setPartImgUrl(cursor.getString(cursor.getColumnIndexOrThrow(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_IMG_URL_STRING)));
                part.setPartColor(cursor.getString(cursor.getColumnIndexOrThrow(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_COLOR_STRING)));

                partsList.add(part);

            } while (cursor.moveToNext());

        }
        return partsList;
    }

    public HashMap<Long, String> selectStringQuery(String columnType, int startId, int endId) {
        HashMap<Long, String> queryResult = new HashMap<Long, String>();

        switch(columnType) {
            case CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_CAT_ID_INTEGER:
                throw new IllegalArgumentException("COLUMN_NAME_PART_PART_CAT_ID_INTEGER point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
        }

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                columnType,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntrySinglePart.COLUMN_NAME_SINGLE_PARTS,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {

            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(CreateTable.TableEntrySinglePart._ID));

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
            case CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_SET_NUM_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_PART_SET_NUM_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_NUM_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_PART_PART_NUM_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_NAME_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_PART_PART_NAME_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_URL_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_PART_PART_URL_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_IMG_URL_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_PART_PART_IMG_URL_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_PART_COLOR_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_PART_PART_COLOR_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");

        }

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                columnType,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntrySinglePart.COLUMN_NAME_SINGLE_PARTS,   // The table to query
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

    public DbHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSetNum() {
        return setNum;
    }

    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }

    public String getPartNum() {
        return partNum;
    }

    public void setPartNum(String partNum) {
        this.partNum = partNum;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Integer getPartCatId() {
        return partCatId;
    }

    public void setPartCatId(Integer partCatId) {
        this.partCatId = partCatId;
    }

    public String getPartUrl() {
        return partUrl;
    }

    public void setPartUrl(String partUrl) {
        this.partUrl = partUrl;
    }

    public String getPartImgUrl() {
        return partImgUrl;
    }

    public void setPartImgUrl(String partImgUrl) {
        this.partImgUrl = partImgUrl;
    }

    public String getPartColor() {
        return partColor;
    }

    public void setPartColor(String partColor) {
        this.partColor = partColor;
    }
}
