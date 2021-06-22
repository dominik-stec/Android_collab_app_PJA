package com.example.mylego.ui.collection;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbMinifigsManager;
import com.example.mylego.database.DbSetNumManager;
import java.util.HashMap;
import java.util.Map;

public class MyCollectionViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    String setNames = "SAMPLE MINIFIGS NAMES: ";
    String setNum = "SET_NUMBERS: ";
    String setNumForMinifig = "SET NUMBER FOR MINIFIG: ";

    public MyCollectionViewModel(@NonNull Application application) {
        super(application);

        //read from database
        DbMinifigsManager db = new DbMinifigsManager(getApplication().getApplicationContext());
        HashMap<Long, String> minifigName = db.selectStringQuery(CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_NAME_STRING, 0, 3);
        for(Map.Entry<Long, String> entry : minifigName.entrySet()) {
            setNames = setNames.concat(entry.getValue() + ", ");
        }
        DbSetNumManager dbSetNum = new DbSetNumManager(getApplication().getApplicationContext());
        HashMap<Long, String> setNameMap = dbSetNum.selectStringQuery(CreateTable.TableEntrySetNum.COLUMN_NAME_SETNUM_SET_NUM_STRING, 0, 3);
        for(Map.Entry<Long, String> entry : setNameMap.entrySet()) {
            setNum = setNum.concat(entry.getValue() + ", ");
        }
        HashMap<Long, String> minifigSetNum = db.selectStringQuery(CreateTable.TableEntryMinifigs.COLUMN_NAME_SET_NUM_STRING, 0, 3);
        for(Map.Entry<Long, String> entry : minifigSetNum.entrySet()) {
            setNumForMinifig = setNumForMinifig.concat(entry.getValue() + ", ");
        }

        mText = new MutableLiveData<>();
        mText.setValue(setNames + " " + setNum + " " + setNumForMinifig);
    }

    public LiveData<String> getText() {
        return mText;
    }
}