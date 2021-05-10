//package com.example.mylego.content_providers;
//
////package com.example.contentprovidersinandroid;
//
//import android.content.ContentProvider;
//import android.content.ContentUris;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.UriMatcher;
//import android.com.example.mylego.database.Cursor;
//import android.com.example.mylego.database.sqlite.SQLiteDatabase;
//import android.com.example.mylego.database.sqlite.SQLiteException;
//import android.com.example.mylego.database.sqlite.SQLiteOpenHelper;
//import android.com.example.mylego.database.sqlite.SQLiteQueryBuilder;
//import android.net.Uri;
//import android.util.Log;
//
//import java.util.HashMap;
//
//public class MyContentProvider extends ContentProvider {
//    public MyContentProvider() {
//        CONTENT_URI = Uri.parse(URL);
//    }
//
//    // defining authority so that other application can access it
//    static final String PROVIDER_NAME = "com.demo.user.provider";
//
//    // defining content URI
//    static final String URL = "content://" + PROVIDER_NAME + "/users";
//
//    // parsing the content URI
//    public static Uri CONTENT_URI = Uri.parse(URL);
//
//    public static final String id = "id";
//    public static final String name = "name";
//    static final int uriCode = 1;
//    static final UriMatcher uriMatcher;
//    public static HashMap<String, String> values;
//
//    ///////////////////////////////////
//    public static final String test_name = "test_name";
//
//    static {
//        values = new HashMap<String, String>();
//        values.put(id, id);
//        values.put(name, name);
//        values.put(test_name, test_name);
//    }
//
//    static {
//
//        // to match the content URI
//        // every time user access table under content provider
//        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//
//        // to access whole table
//        uriMatcher.addURI(PROVIDER_NAME, "users", uriCode);
//
//        // to access a particular row
//        // of the table
//        uriMatcher.addURI(PROVIDER_NAME, "users/*", uriCode);
//    }
//    @Override
//    public String getType(Uri uri) {
//        switch (uriMatcher.match(uri)) {
//            case uriCode:
//                return "vnd.android.cursor.dir/users";
//            default:
//                throw new IllegalArgumentException("Unsupported URI: " + uri);
//        }
//    }
//    // creating the com.example.mylego.database
//    @Override
//    public boolean onCreate() {
//        Context context = getContext();
//        DatabaseHelper dbHelper = new DatabaseHelper(context);
//        db = dbHelper.getWritableDatabase();
//        if (db != null) {
//            //dbHelper.onUpgrade(db,1,1);
//            return true;
//        }
//        return false;
//    }
//    @Override
//    public Cursor query(Uri uri, String[] projection, String selection,
//                        String[] selectionArgs, String sortOrder) {
//        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
//        qb.setTables(TABLE_NAME);
//        switch (uriMatcher.match(uri)) {
//            case uriCode:
//                qb.setProjectionMap(values);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI " + uri);
//        }
//        if (sortOrder == null || sortOrder == "") {
//            sortOrder = id;
//        }
//        Cursor c = qb.query(db, projection, selection, selectionArgs, null,
//                null, sortOrder);
//        c.setNotificationUri(getContext().getContentResolver(), uri);
//        return c;
//    }
//
//    // adding data to the com.example.mylego.database
//    @Override
//        public Uri insert(Uri uri, ContentValues values) {
//        long rowID = db.insert(TABLE_NAME, "", values);
//        if (rowID > 0) {
//            Log.i("FROM INSERT", "rowID > 0");
//            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
//            getContext().getContentResolver().notifyChange(_uri, null);
//            return _uri;
//        }
//        throw new SQLiteException("Failed to add a record into " + uri);
//    }
//
//    @Override
//    public int update(Uri uri, ContentValues values, String selection,
//                      String[] selectionArgs) {
//        int count = 0;
//        switch (uriMatcher.match(uri)) {
//            case uriCode:
//                count = db.update(TABLE_NAME, values, selection, selectionArgs);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI " + uri);
//        }
//        getContext().getContentResolver().notifyChange(uri, null);
//        return count;
//    }
//
//    @Override
//    public int delete(Uri uri, String selection, String[] selectionArgs) {
//        int count = 0;
//        switch (uriMatcher.match(uri)) {
//            case uriCode:
//                count = db.delete(TABLE_NAME, selection, selectionArgs);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI " + uri);
//        }
//        getContext().getContentResolver().notifyChange(uri, null);
//        return count;
//    }
//
//    // creating object of com.example.mylego.database
//    // to perform query
//    private SQLiteDatabase db;
//
//    // declaring name of the com.example.mylego.database
//    static final String DATABASE_NAME = "UserDB2";
//
//    // declaring table name of the com.example.mylego.database
//    static final String TABLE_NAME = "Users7";
//
//    // declaring version of the com.example.mylego.database
//    static final int DATABASE_VERSION = 4;
//
//    // sql query to create the table
//    static final String CREATE_DB_TABLE = " CREATE TABLE " + TABLE_NAME
//            + " (id INTEGER PRIMARY KEY, "
//            + " test TEXT NOT NULL, "
//            + " test_name TEXT NOT NULL);";
//
//    // creating a com.example.mylego.database
//    private static class DatabaseHelper extends SQLiteOpenHelper {
//
//        // defining a constructor
//        DatabaseHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        }
//
//        // creating a table in the com.example.mylego.database
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//
//            db.execSQL(CREATE_DB_TABLE);
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//            // sql query to drop a table
//            // having similar name
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//            onCreate(db);
//        }
//    }
//}
