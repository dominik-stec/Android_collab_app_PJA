package com.example.mylego.ui.sets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SetsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SetsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is sets fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}