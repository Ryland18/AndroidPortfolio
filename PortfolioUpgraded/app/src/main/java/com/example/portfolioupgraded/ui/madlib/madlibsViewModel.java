package com.example.portfolioupgraded.ui.madlib;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class madlibsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public madlibsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Madlibs fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}