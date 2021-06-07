package com.example.mylego.ui.collection;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyCollectionViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    public MyCollectionViewModel(@NonNull Application application) {
        super(application);

        mText = new MutableLiveData<>();
        mText.setValue("This is my collection fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}