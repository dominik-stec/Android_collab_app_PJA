package com.example.mylego.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mylego.rest.domain.BricksSingleSet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DbMyCollectionManager {
    private final DbHelper dbHelper;

    //=== CONSTRUCTORS =============================================================================
    public DbMyCollectionManager(Context appContext) {
        this.dbHelper = new DbHelper(appContext);
    }

    //=== PUBLIC METHODS ===========================================================================
    public long commitIntoDb(BricksSingleSet setToInsert) {
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CreateTable.TableMyCollection.COLUMN_NAME_SET_NUM_STRING, setToInsert.getSet_number());
        values.put(CreateTable.TableMyCollection.COLUMN_NAME_NAME_STRING, setToInsert.getName());
        values.put(CreateTable.TableMyCollection.COLUMN_NAME_YEAR_INTEGER, setToInsert.getYear());
        values.put(CreateTable.TableMyCollection.COLUMN_NAME_THEME_ID_INTEGER, setToInsert.getTheme_id());
        values.put(CreateTable.TableMyCollection.COLUMN_NAME_NUM_PARTS_INTEGER, setToInsert.getNumber_of_parts());
        values.put(CreateTable.TableMyCollection.COLUMN_NAME_SET_IMG_URL_STRING, setToInsert.getImage_url());
        values.put(CreateTable.TableMyCollection.COLUMN_NAME_SET_URL_STRING, setToInsert.getSet_url());
        values.put(CreateTable.TableMyCollection.COLUMN_NAME_LAST_MODIFIED_DT_STRING, setToInsert.getModification_date());

        return dbWrite.insert(CreateTable.TableMyCollection.TABLE_NAME, null, values);
    }

    //==============================================================================================
    public  ArrayList<BricksSingleSet> getAllSets() {
        return getAllSets(0);
    }

    //==============================================================================================
    public ArrayList<BricksSingleSet> getAllSets(int limit) {
        ArrayList<BricksSingleSet> results = new ArrayList<>();
        ArrayList<Map<String, String>> entriesAsMap = getAllEntries(limit);

        if (entriesAsMap == null)
            return null;

        entriesAsMap.forEach(entryAsMapItem -> {
            BricksSingleSet entryAsSingleSetObject = convertMapSetToBricksSingleSet(entryAsMapItem);
            results.add(entryAsSingleSetObject);
        });

        return results;
    }

    //==============================================================================================
    public ArrayList<Map<String, String>> getAllEntries() {
        return getAllEntries(0);
    }

    // =============================================================================================
    public ArrayList<Map<String, String>> getAllEntries(int limit) {
        Log.d("DbMyCollectionManager-getAllEntries", String.format("==> getAllSets"));

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;

        String sortOrder = CreateTable.TableMyCollection._ID + " ASC";
        Cursor cursor = dbRead.query(
                CreateTable.TableMyCollection.TABLE_NAME,
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,           // don't group the rows
                null,            // don't filter by row groups
                sortOrder,
                limit > 0 ? String.valueOf(limit) : null
        );

        return getQueryResults(cursor);
    }

    //==============================================================================================
    public ArrayList<Map<String, String>> getQueryResults(Cursor cursor) {
        Log.d("DbMyCollectionManager-getQueryResults", String.format("==> getQueryResults"));
        ArrayList<Map<String, String>> results = new ArrayList<>();

        if (!cursor.moveToFirst())
            return null;

        for (int rowIndex = 0; rowIndex < cursor.getCount(); rowIndex++) {
            Map<String, String> rowData;
            rowData = getQueryResultAsSingleDbRow(cursor);
            results.add(rowData);
            cursor.moveToNext();
        }

        cursor.close();
        return results;
    }

    //==============================================================================================
    public Map<String, String> getQueryResultAsSingleDbRow(Cursor cursor) {
        Map<String, String> result = new HashMap<>();

        for (int columnIndex = 0; columnIndex < cursor.getColumnCount(); columnIndex++) {
            String currentCursorColumnName = cursor.getColumnName(columnIndex);
            String currentCursorColumnValue = cursor.getString(columnIndex);
            Log.d("DbMyCollectionManager-getQueryResultAsSingleDbRow", String.format("Column name: %s\tValue: %s", currentCursorColumnName, currentCursorColumnValue));
            result.put(currentCursorColumnName, currentCursorColumnValue);
        }
        return result;
    }

    //==============================================================================================
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
