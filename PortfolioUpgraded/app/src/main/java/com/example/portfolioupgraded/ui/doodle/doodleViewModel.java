package com.example.portfolioupgraded.ui.doodle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class doodleViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public doodleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Doodle");
    }

    public LiveData<String> getText() {
        return mText;
    }
}