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
import com.example.mylego.database.DbSetNumManager;

import java.util.HashMap;
import java.util.Map;

public class MyCollectionViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    String setNames = "SAMPLE MINIFIGS NAMES: ";
    String setNum = "SET_NUMBERS: ";

    public MyCollectionViewModel(@NonNull Application application) {
        super(application);

        //read from database
        DbMinifigsManager db = new DbMinifigsManager(getApplication().getApplicationContext());
        HashMap<Long, String> minifigName = db.selectStringQuery(CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_NAME_STRING, 0, 3);
        for(Map.Entry<Long, String> entry : minifigName.entrySet()) {
            Log.d("query minifig string: ", "id string " + entry.getKey() + " " + entry.getValue());
            setNames = setNames.concat(entry.getValue() + ", ");
        }
        DbSetNumManager dbSetNum = new DbSetNumManager(getApplication().getApplicationContext());
        HashMap<Long, String> setNameMap = dbSetNum.selectStringQuery(CreateTable.TableEntrySetNum.COLUMN_NAME_SETNUM_SET_NUM_STRING, 0, 3);
        for(Map.Entry<Long, String> entry : setNameMap.entrySet()) {
            //Log.d("query minifig string: ", "id string " + entry.getKey() + " " + entry.getValue());
            setNum = setNum.concat(entry.getValue() + ", ");
        }

        mText = new MutableLiveData<>();
        mText.setValue(setNames + " " + setNum);
    }

    public LiveData<String> getText() {
        return mText;
    }
}