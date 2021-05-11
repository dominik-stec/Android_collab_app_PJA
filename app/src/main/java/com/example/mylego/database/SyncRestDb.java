package com.example.mylego.database;

import android.app.Application;

public class SyncRestDb extends Application {
    private static SyncRestDb singleton = new SyncRestDb();;

    public static SyncRestDb getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    private static int insertionCounter;
    public static int getData() {return insertionCounter;}
    public static void setData(int insCount) {insertionCounter = insCount;}

//    private static final SyncRestDb holder = new SyncRestDb();
//    public static SyncRestDb getInstance() {return holder;}
}
