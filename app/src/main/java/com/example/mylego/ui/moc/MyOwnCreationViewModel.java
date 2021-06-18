package com.example.mylego.ui.moc;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbPartsManager;
import com.example.mylego.database.DbSinglePartsManager;
import com.example.mylego.rest.domain.Part;
import com.example.mylego.rest.domain.PartsSingleSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyOwnCreationViewModel extends AndroidViewModel {
    private MutableLiveData<String> mText;
    String parts = "SINGLE PARTS SET NUMS: ";

    public MyOwnCreationViewModel(@NonNull Application application) {
        super(application);

        //read from database
        DbSinglePartsManager db = new DbSinglePartsManager(getApplication().getApplicationContext());
//        ArrayList<Part> SinglePartsList = db.getSinglePartsBySetNum("003-1");
        //        ArrayList<PartsSingleSet> setName = db.selectStringQuery(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ID_INTEGER,0,5);
        HashMap<Long, String> partName = db.selectStringQuery(CreateTable.TableEntrySinglePart.COLUMN_NAME_PART_SET_NUM_STRING,0,5);

        for(Map.Entry<Long, String> entry : partName.entrySet()) {
            parts = parts.concat(entry.getValue() + ", ");
        }

//        for(Part entry : SinglePartsList) {
//            parts = parts.concat("  SET_NUM: " + entry.getSetNum() + ", ");
//            parts = parts.concat("  COLOR: " + entry.getPartColor() + ", ");
//            parts = parts.concat("  NAME: " + entry.getPartName() + ", ");
//            parts = parts.concat("  URL: " + entry.getPartImgUrl() + ", ");
//            if(parts!="") break;
//        }

        mText = new MutableLiveData<>();
        mText.setValue(parts);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
