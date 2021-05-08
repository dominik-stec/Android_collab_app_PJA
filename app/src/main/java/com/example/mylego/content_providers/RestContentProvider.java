package com.example.mylego.content_providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

public class RestContentProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "com.example.mylego.content_providers.restcontentprovider";
    public static final String URL = "content://" + PROVIDER_NAME + "/bricks";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String _ID = "_id";
    public static final String SET_NUM = "set_num";
    public static final String NAME = "name";


    private static HashMap<String, String> BRICKS_PROJECTION_MAP;

    public static final int BRICKS = 1;
    public static final int BRICKS_ID = 2;

    public static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "bricks", BRICKS);
        uriMatcher.addURI(PROVIDER_NAME, "bricks/#", BRICKS_ID);
    }

    /**
     * Database specific constant declarations
     */

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "BricksDB";
    static final String BRICKS_TABLE_NAME = "bricks";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + BRICKS_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " set_num TEXT NOT NULL, " +
                    " name TEXT NOT NULL);";

    /**
     * Helper class that actually creates and manages
     * the provider's underlying data repository.
     */

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  BRICKS_TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * Add a new student record
         */
        long rowID = db.insert(	BRICKS_TABLE_NAME, "", values);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(BRICKS_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case BRICKS:
                qb.setProjectionMap(BRICKS_PROJECTION_MAP);
                break;

            case BRICKS_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = NAME;
        }

        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case BRICKS:
                count = db.delete(BRICKS_TABLE_NAME, selection, selectionArgs);
                break;

            case BRICKS_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete( BRICKS_TABLE_NAME, _ID +  " = " + id +
                                (!TextUtils.isEmpty(selection) ?
                        "AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case BRICKS:
                count = db.update(BRICKS_TABLE_NAME, values, selection, selectionArgs);
                break;

            case BRICKS_ID:
                count = db.update(BRICKS_TABLE_NAME, values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ?
                        "AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case BRICKS:
                return "vnd.android.cursor.dir/vnd.example.bricks";
            /**
             * Get a particular student
             */
            case BRICKS_ID:
                return "vnd.android.cursor.item/vnd.example.bricks";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}

