package com.example.portfolioupgraded.ui.guessanumber;


package com.example.portfolioupgraded.ui.number;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class guessanumberViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public guessanumberViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}