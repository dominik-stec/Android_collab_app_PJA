package com.example.mylego.ui.moc;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyOwnCreationViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MyOwnCreationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is MOC fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
