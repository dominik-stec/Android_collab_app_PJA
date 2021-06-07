package com.example.mylego.ui.moc;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyOwnCreationViewModel extends AndroidViewModel {
    private MutableLiveData<String> mText;

    public MyOwnCreationViewModel(@NonNull Application application) {
        super(application);

        mText = new MutableLiveData<>();
        mText.setValue("This is MOC fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
