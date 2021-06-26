package com.example.mylego.ui.collection;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mylego.database.DbManager;
import com.example.mylego.database.DbMyCollectionManager;
import com.example.mylego.rest.domain.BricksSingleSet;

import java.util.ArrayList;

public class MyCollectionViewModel extends AndroidViewModel {
    private DbMyCollectionManager _dbMyCollectionManager;
    private DbManager _dbAllSetsManager;

    private MutableLiveData<String> _ownSetsCount;

    private ArrayList<BricksSingleSet> _myCollectionSetsList;

    public MyCollectionViewModel(@NonNull Application application) {
        super(application);

        Context appContext = application.getApplicationContext();

        _dbMyCollectionManager = new DbMyCollectionManager(appContext);
        _dbAllSetsManager = new DbManager(appContext);

        _ownSetsCount = new MutableLiveData<>();

        _myCollectionSetsList = initCollectionDb();

    }

    //=== PUBLIC METHODS ===========================================================================
    public LiveData<String> getOwnSetsCount() {
        return this._ownSetsCount;
    }

    private ArrayList<BricksSingleSet> initCollectionDb() {
        ArrayList<BricksSingleSet> resultCollectionList;
        ArrayList<BricksSingleSet> firstSet = _dbMyCollectionManager.getAllSets(1);


        if (firstSet == null) {
            Log.d("MyCollectionView-initCollectionDb", "DB EMPTY");
            ArrayList<BricksSingleSet> setsToInsert = _dbAllSetsManager.getAllSets(5);

            for (BricksSingleSet singleSetToInsert : setsToInsert) {
                _dbMyCollectionManager.commitIntoDb(singleSetToInsert);
            }
        }

        resultCollectionList = _dbMyCollectionManager.getAllSets();
        _ownSetsCount.postValue(String.valueOf(resultCollectionList.size()));

        return resultCollectionList;
    }
}