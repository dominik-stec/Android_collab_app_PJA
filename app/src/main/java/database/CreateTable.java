package database;

import android.provider.BaseColumns;

public final class CreateTable {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private CreateTable() {}

    /* Inner class that defines the table contents */
    public static class TableEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TableEntry.TABLE_NAME + " (" +
                    TableEntry._ID + " INTEGER PRIMARY KEY," +
                    TableEntry.COLUMN_NAME_TITLE + " TEXT," +
                    TableEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TableEntry.TABLE_NAME;

}
