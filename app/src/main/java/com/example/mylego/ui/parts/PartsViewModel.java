package com.example.mylego.ui.parts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PartsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PartsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Search parts");
    }

    public LiveData<String> getText() {
        return mText;
    }
}