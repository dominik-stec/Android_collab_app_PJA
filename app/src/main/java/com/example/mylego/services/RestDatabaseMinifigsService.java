package com.example.mylego.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;
import com.example.mylego.database.DbMinifigsManager;
import com.example.mylego.rest.IFromRestCallback;
import com.example.mylego.rest.controllers.RestOnePageBricksCtrl;
import com.example.mylego.rest.controllers.RestOnePageMinifigsCtrl;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.domain.MinifigsSingleSet;

import java.util.HashMap;
import java.util.Map;

public class RestDatabaseMinifigsService extends IntentService {



    public RestDatabaseMinifigsService(){
        super("RestDatabaseMinifigsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {




    }

}
