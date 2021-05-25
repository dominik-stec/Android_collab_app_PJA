package com.example.mylego.database;

import android.provider.BaseColumns;

public final class CreateTable {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private CreateTable() {}

    /* Inner class that defines the table contents */
    public static class TableEntry implements BaseColumns {
        public static final String TABLE_NAME = "brick_set";
        public static final String COLUMN_NAME_SET_NUM_STRING = "set_number";
        public static final String COLUMN_NAME_NAME_STRING = "name";
        public static final String COLUMN_NAME_YEAR_INTEGER = "year";
        public static final String COLUMN_NAME_THEME_ID_INTEGER = "theme_id";
        public static final String COLUMN_NAME_NUM_PARTS_INTEGER = "number_of_parts";
        public static final String COLUMN_NAME_SET_IMG_URL_STRING = "image_url";
        public static final String COLUMN_NAME_SET_URL_STRING= "set_url";
        public static final String COLUMN_NAME_LAST_MODIFIED_DT_STRING = "modification_date";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TableEntry.TABLE_NAME + " (" +
                    TableEntry._ID + " INTEGER PRIMARY KEY," +
                    TableEntry.COLUMN_NAME_SET_NUM_STRING + " TEXT," +
                    TableEntry.COLUMN_NAME_NAME_STRING + " TEXT," +
                    TableEntry.COLUMN_NAME_YEAR_INTEGER + " INTEGER," +
                    TableEntry.COLUMN_NAME_THEME_ID_INTEGER + " INTEGER," +
                    TableEntry.COLUMN_NAME_NUM_PARTS_INTEGER + " INTEGER," +
                    TableEntry.COLUMN_NAME_SET_IMG_URL_STRING + " TEXT," +
                    TableEntry.COLUMN_NAME_SET_URL_STRING + " TEXT," +
                    TableEntry.COLUMN_NAME_LAST_MODIFIED_DT_STRING + " TEXT)";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TableEntry.TABLE_NAME;

}
