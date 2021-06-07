package com.example.mylego.ui.parts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PartsViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    public PartsViewModel(@NonNull Application application) {
        super(application);

        mText = new MutableLiveData<>();
        mText.setValue("This is parts fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}