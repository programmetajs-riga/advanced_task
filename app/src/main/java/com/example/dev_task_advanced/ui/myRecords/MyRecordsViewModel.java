package com.example.dev_task_advanced.ui.myRecords;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyRecordsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyRecordsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}