package com.example.mylego.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    // If you change the com.example.mylego.database schema, you must increment the com.example.mylego.database version.
    public static final int DATABASE_VERSION = 90;
    public static final String DATABASE_NAME = "BricksSet.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTable.SQL_CREATE_TABLE);
        db.execSQL(CreateTable.SQL_CREATE_TABLE_MINIFIGS);
        db.execSQL(CreateTable.SQL_CREATE_TABLE_SETNUM);
        db.execSQL(CreateTable.SQL_CREATE_TABLE_PARTS);
        db.execSQL(CreateTable.SQL_CREATE_TABLE_SINGLE_PARTS);
        db.execSQL(CreateTable.SQL_CREATE_TABLE_COLOR);
        db.execSQL(CreateTable.SQL_CREATE_TABLE_SUBTABLES_PART);
        db.execSQL(CreateTable.SQL_CREATE_TABLE_SUBTABLES_COLOR);
        db.execSQL(CreateTable.SQL_CREATE_TABLE_MY_SETS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This com.example.mylego.database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(CreateTable.SQL_DELETE_TABLE);
        db.execSQL(CreateTable.SQL_DELETE_TABLE_MINIFIGS);
        db.execSQL(CreateTable.SQL_DELETE_TABLE_SETNUM);
        db.execSQL(CreateTable.SQL_DELETE_TABLE_PARTS);
        db.execSQL(CreateTable.SQL_DELETE_TABLE_SINGLE_PARTS);
        db.execSQL(CreateTable.SQL_DELETE_TABLE_COLOR);
        db.execSQL(CreateTable.SQL_DELETE_TABLE_SUBTABLES_PART);
        db.execSQL(CreateTable.SQL_DELETE_TABLE_SUBTABLES_COLOR);
        db.execSQL(CreateTable.SQL_DELETE_TABLE_MY_SETS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
