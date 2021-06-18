package com.example.mylego.ui.sets;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;
import java.util.HashMap;
import java.util.Map;

//change from ViewModel to AndroidViewModel for get access to getApplicationContext() method
public class SetsViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    String setNames = "SAMPLE SETS NAMES: ";

    public SetsViewModel(@NonNull Application application) {
        super(application);

        //read from database
        DbManager db = new DbManager(getApplication().getApplicationContext());
        HashMap<Long, String> setName = db.selectStringQuery(CreateTable.TableEntry.COLUMN_NAME_NAME_STRING, 0, 5);
        for(Map.Entry<Long, String> entry : setName.entrySet()) {
            setNames = setNames.concat(entry.getValue() + ", ");
        }

        mText = new MutableLiveData<>();
        mText.postValue(setNames);

    }

    public LiveData<String> getText() {
        return mText;
    }
}