package com.example.mylego.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.mylego.rest.domain.BricksSingleSet;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    //----------------------------------------------------------------------------------------------
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

    //----------------------------------------------------------------------------------------------
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

    //----------------------------------------------------------------------------------------------
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

    //----------------------------------------------------------------------------------------------
    public ArrayList<BricksSingleSet> getAllSets() {
        ArrayList<BricksSingleSet> results = new ArrayList<>();
        ArrayList<Map<String, String>> entriesAsMap = getAllEntries();

        entriesAsMap.forEach(entryAsMapItem -> {
            BricksSingleSet entryAsSingleSetObject = convertMapSetToBricksSingleSet(entryAsMapItem);
            results.add(entryAsSingleSetObject);
        });

        return results;
    }

    // ---------------------------------------------------------------------------------------------
    public ArrayList<Map<String, String>> getAllEntries() {
        Log.d("DEBUG", String.format("==> getAllSets"));

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;

        String sortOrder = CreateTable.TableEntry._ID + " ASC";
        Cursor cursor = dbRead.query(
                CreateTable.TableEntry.TABLE_NAME,
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,           // don't group the rows
                null,            // don't filter by row groups
                sortOrder
        );

        return getQueryResults(cursor);
    }

    //----------------------------------------------------------------------------------------------
    public ArrayList<BricksSingleSet> getSetsByName(String name) {
        ArrayList<BricksSingleSet> results = new ArrayList<>();
        ArrayList<Map<String, String>> entriesAsMap = getEntriesByName(name);

        entriesAsMap.forEach(entryAsMapItem -> {
            BricksSingleSet entryAsSingleSetObject = convertMapSetToBricksSingleSet(entryAsMapItem);
            results.add(entryAsSingleSetObject);
        });

        return results;
    }

    // ---------------------------------------------------------------------------------------------
    public ArrayList<Map<String, String>> getEntriesByName(String setName) {
        Log.d("DEBUG", String.format("==> getEntryByName: %s", setName));

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                CreateTable.TableEntry.COLUMN_NAME_SET_NUM_STRING,
                CreateTable.TableEntry.COLUMN_NAME_NAME_STRING,
                CreateTable.TableEntry.COLUMN_NAME_YEAR_INTEGER,
                CreateTable.TableEntry.COLUMN_NAME_THEME_ID_INTEGER,
                CreateTable.TableEntry.COLUMN_NAME_NUM_PARTS_INTEGER,
                CreateTable.TableEntry.COLUMN_NAME_SET_IMG_URL_STRING,
                CreateTable.TableEntry.COLUMN_NAME_SET_URL_STRING,
                CreateTable.TableEntry.COLUMN_NAME_LAST_MODIFIED_DT_STRING,
        };

        String selection = CreateTable.TableEntry.COLUMN_NAME_NAME_STRING + " = ?";
        String[] selectionArgs = { setName };

        String sortOrder = CreateTable.TableEntry._ID + " ASC";
        Cursor cursor = dbRead.query(
                CreateTable.TableEntry.TABLE_NAME,
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,           // don't group the rows
                null,            // don't filter by row groups
                sortOrder
        );

        return getQueryResults(cursor);
    }

    //----------------------------------------------------------------------------------------------
    public ArrayList<Map<String, String>> getQueryResults(Cursor cursor) {
        Log.d("DEBUG", String.format("==> getQueryResults"));
        ArrayList<Map<String, String>> results = new ArrayList<>();

        cursor.moveToFirst();
        for (int rowIndex = 0; rowIndex < cursor.getCount(); rowIndex++) {
            Map<String, String> rowData;
            rowData = getQueryResultAsSingleDbRow(cursor);
            results.add(rowData);
            cursor.moveToNext();
        }

        cursor.close();
        return results;
    }

    //----------------------------------------------------------------------------------------------
    public Map<String, String> getQueryResultAsSingleDbRow(Cursor cursor) {
        Map<String, String> result = new HashMap<>();

        for (int columnIndex = 0; columnIndex < cursor.getColumnCount(); columnIndex++) {
            String currentCursorColumnName = cursor.getColumnName(columnIndex);
            String currentCursorColumnValue = cursor.getString(columnIndex);
            Log.d("DEBUG", String.format("Column name: %s\tValue: %s", currentCursorColumnName, currentCursorColumnValue));
            result.put(currentCursorColumnName, currentCursorColumnValue);
        }
        return result;
    }

    //----------------------------------------------------------------------------------------------
    public BricksSingleSet convertMapSetToBricksSingleSet(Map<String, String> singleSetAsMap) {
        BricksSingleSet singleSet = new BricksSingleSet();
        List<Method> singleSetMethods = Arrays.asList(singleSet.getClass().getDeclaredMethods());

        for (Map.Entry<String, String> singleSetDbColumnAsEntry : singleSetAsMap.entrySet()) {
            String columnName = singleSetDbColumnAsEntry.getKey().toLowerCase();
            String columnValue = singleSetDbColumnAsEntry.getValue();

            Method singleSetMatchedMethod = singleSetMethods
                    .stream()
                    .filter(method -> method
                            .getName()
                            .toLowerCase()
                            .equals(String.format("set%s", columnName)))
                    .collect(Collectors.toList()).get(0);

            Class<?> singleSetMethodParameterType = singleSetMatchedMethod.getParameterTypes()[0];

            Object parsedColumnValue = castColumnValueToType(singleSetMethodParameterType, columnValue);

            try {
                singleSetMatchedMethod.invoke(singleSet, parsedColumnValue);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return singleSet;
    }

    //----------------------------------------------------------------------------------------------
    public <Any> Any castColumnValueToType(Class<?> type, String columnValue) {
        return (Any) (
                type.getName().equals("int")
                        ? Integer.parseInt(columnValue)
                        : (type.getName().equals("long")
                            ? Long.parseLong(columnValue)
                            : columnValue
                )
        );
    }
}




