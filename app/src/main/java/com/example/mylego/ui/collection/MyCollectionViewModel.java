package com.example.mylego.ui.collection;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;
import com.example.mylego.database.DbMinifigsManager;

import java.util.HashMap;
import java.util.Map;

public class MyCollectionViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    String setNames = "SAMPLE MINIFIGS NAMES: ";

    public MyCollectionViewModel(@NonNull Application application) {
        super(application);

        //read from database
        DbMinifigsManager db = new DbMinifigsManager(getApplication().getApplicationContext());
        HashMap<Long, String> minifigName = db.selectStringQuery(CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_NAME_STRING, 0, 3);
        for(Map.Entry<Long, String> entry : minifigName.entrySet()) {
            Log.d("query minifig string: ", "id string " + entry.getKey() + " " + entry.getValue());
            setNames = setNames.concat(entry.getValue() + ", ");
        }

        mText = new MutableLiveData<>();
        mText.setValue(setNames);
    }

    public LiveData<String> getText() {
        return mText;
    }
}