package com.app.coronatracker.ui.home.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.coronatracker.ui.api.DataRepository;
import com.app.coronatracker.ui.home.model.Dashboard;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Dashboard> mutableLiveData;
    private DataRepository dataRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        dataRepository = DataRepository.getInstance();
        mutableLiveData = dataRepository.getData();
    }

    public LiveData<Dashboard> getDataRepository() {
        return mutableLiveData;
    }
}