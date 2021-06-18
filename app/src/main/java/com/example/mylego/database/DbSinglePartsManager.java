package com.example.mylego.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.mylego.rest.domain.BrickLink;
import com.example.mylego.rest.domain.BrickOwl;
import com.example.mylego.rest.domain.Color;
import com.example.mylego.rest.domain.ExternalIdsColor;
import com.example.mylego.rest.domain.ExternalIdsPart;
import com.example.mylego.rest.domain.LDraw;
import com.example.mylego.rest.domain.Lego;
import com.example.mylego.rest.domain.Part;
import com.example.mylego.rest.domain.PartsSingleSet;
import com.example.mylego.rest.domain.Peeron;

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

    public ArrayList<PartsSingleSet> getPartsSingleSetBySetNum(String setNum) {

        ArrayList<PartsSingleSet> partsList = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ID_INTEGER,
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_INV_PART_ID_INTEGER,
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_SET_NUM_STRING,
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_QUANTITY_INTEGER,
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_IS_SPARE_BOOLEAN,
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ELEMENT_ID_STRING,
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_NUM_SETS_INTEGER
        };

        String selection = CreateTable.TableEntryParts.COLUMN_NAME_PARTS_SET_NUM_STRING + "=?";

        String[] selectionArgs = {setNum};

        Cursor cursor = db.query(CreateTable.TableEntryParts.TABLE_NAME_PARTS, projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {

            do {
                PartsSingleSet partsSingleSet = new PartsSingleSet();

                partsSingleSet.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ID_INTEGER)));
                partsSingleSet.setInvPartId(cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ID_INTEGER)));
                partsSingleSet.setSetNum(setNum);
                partsSingleSet.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_QUANTITY_INTEGER)));
                boolean isSpare = cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_IS_SPARE_BOOLEAN)) == 1;
                partsSingleSet.setSpare(isSpare);
                partsSingleSet.setElementId(cursor.getString(cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_QUANTITY_INTEGER))));
                partsSingleSet.setNumSets(cursor.getInt(cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_QUANTITY_INTEGER))));

                partsList.add(partsSingleSet);

            } while (cursor.moveToNext());

        }
        return partsList;
    }

    public HashMap<Long, String> selectStringQuery(String columnType, int startId, int endId) {
        HashMap<Long, String> queryResult = new HashMap<Long, String>();

        switch(columnType) {
            case CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ID_INTEGER:
                throw new IllegalArgumentException("COLUMN_NAME_PARTS_ID_INTEGER point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
            case CreateTable.TableEntryParts.COLUMN_NAME_PARTS_INV_PART_ID_INTEGER:
                throw new IllegalArgumentException("COLUMN_NAME_PARTS_INV_PART_ID_INTEGER point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
            case CreateTable.TableEntryParts.COLUMN_NAME_PARTS_QUANTITY_INTEGER:
                throw new IllegalArgumentException("COLUMN_NAME_PARTS_QUANTITY_INTEGER point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
            case CreateTable.TableEntryParts.COLUMN_NAME_PARTS_IS_SPARE_BOOLEAN:
                throw new IllegalArgumentException("COLUMN_NAME_PARTS_IS_SPARE_BOOLEAN point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
            case CreateTable.TableEntryParts.COLUMN_NAME_PARTS_NUM_SETS_INTEGER:
                throw new IllegalArgumentException("COLUMN_NAME_PARTS_NUM_SETS_INTEGER point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
        }

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                columnType,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntryParts.TABLE_NAME_PARTS,   // The table to query
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
            case CreateTable.TableEntryParts.COLUMN_NAME_PARTS_SET_NUM_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_PARTS_SET_NUM_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ELEMENT_ID_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_PARTS_ELEMENT_ID_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
        }

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                columnType,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntryParts.TABLE_NAME_PARTS,   // The table to query
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
