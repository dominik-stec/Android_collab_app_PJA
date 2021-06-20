package com.example.mylego.ui.sets;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;
import com.example.mylego.rest.domain.BricksSingleSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//change from ViewModel to AndroidViewModel for get access to getApplicationContext() method
public class SetsViewModel extends AndroidViewModel {
    private ArrayList<BricksSingleSet> _setsFromDbSearch;
    private ArrayList<BricksSingleSet> _setsFromDbAll;

    private MutableLiveData<String> mText;
    String setNames = "SAMPLE SETS NAMES: ";

    public SetsViewModel(@NonNull Application application) {
        super(application);

        //read from database
        DbManager db = new DbManager(getApplication().getApplicationContext());
        HashMap<Long, String> setName = db.selectStringQuery(CreateTable.TableEntry.COLUMN_NAME_NAME_STRING, 0, 5);
        for (Map.Entry<Long, String> entry : setName.entrySet()) {
            setNames = setNames.concat(entry.getValue() + ", ");
        }

        mText = new MutableLiveData<>();
        mText.postValue(setNames);

        _setsFromDbSearch = db.getSetsByName("Gears");
        _setsFromDbAll = db.getAllSets();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public BricksSingleSet getBrickSingleSetBySetNum(String setNumber) {
        return _setsFromDbSearch
                .stream()
                .filter(set -> set.getSet_number().equals(setNumber))
                .collect(Collectors.toList()).get(0);
    }
}