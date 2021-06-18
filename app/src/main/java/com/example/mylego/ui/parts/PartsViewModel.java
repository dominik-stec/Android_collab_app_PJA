package com.example.mylego.ui.parts;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbPartsManager;
import java.util.HashMap;
import java.util.Map;

public class PartsViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    String parts = "part ID: ";

    public PartsViewModel(@NonNull Application application) {
        super(application);

        //read from database
        DbPartsManager db = new DbPartsManager(getApplication().getApplicationContext());
        HashMap<Long, Integer> partId = db.selectNumberQuery(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ID_INTEGER,0,3);

        for(Map.Entry<Long, Integer> entry : partId.entrySet()) {
            parts = parts.concat(entry.getValue() + ", ");
        }

        mText = new MutableLiveData<>();
        mText.setValue(parts);
    }

    public LiveData<String> getText() {
        return mText;
    }
}