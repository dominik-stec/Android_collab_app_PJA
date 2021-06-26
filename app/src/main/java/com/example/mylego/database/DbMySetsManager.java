package com.example.mylego.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mylego.rest.domain.BricksSingleSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DbMySetsManager {
    private final DbHelper dbHelper;

    //=== CONSTRUCTORS =============================================================================
    public DbMySetsManager(Context appContext) {
        this.dbHelper = new DbHelper(appContext);
    }

    //=== PUBLIC METHODS ===========================================================================
    public long commitIntoDb(BricksSingleSet setToInsert) {
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CreateTable.TableMySets.COLUMN_NAME_SET_NUM_STRING, setToInsert.getSet_number());
        values.put(CreateTable.TableMySets.COLUMN_NAME_NAME_STRING, setToInsert.getName());
        values.put(CreateTable.TableMySets.COLUMN_NAME_YEAR_INTEGER, setToInsert.getYear());
        values.put(CreateTable.TableMySets.COLUMN_NAME_THEME_ID_INTEGER, setToInsert.getTheme_id());
        values.put(CreateTable.TableMySets.COLUMN_NAME_NUM_PARTS_INTEGER, setToInsert.getNumber_of_parts());
        values.put(CreateTable.TableMySets.COLUMN_NAME_SET_IMG_URL_STRING, setToInsert.getImage_url());
        values.put(CreateTable.TableMySets.COLUMN_NAME_SET_URL_STRING, setToInsert.getSet_url());
        values.put(CreateTable.TableMySets.COLUMN_NAME_LAST_MODIFIED_DT_STRING, setToInsert.getModification_date());

        return dbWrite.insert(CreateTable.TableMySets.TABLE_NAME, null, values);
    }

    //==============================================================================================
    public ArrayList<Map<String, String>> getAllEntries() {
        return getAllEntries(0);
    }

    // =============================================================================================
    public ArrayList<Map<String, String>> getAllEntries(int limit) {
        Log.d("DbMySetsManager-getAllEntries", String.format("==> getAllSets"));

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
                sortOrder,
                limit > 0 ? String.valueOf(limit) : null
        );

        return getQueryResults(cursor);
    }

    //==============================================================================================
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

    //==============================================================================================
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

}
