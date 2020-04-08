package com.app.coronatracker.ui.dashboard.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.coronatracker.ui.api.DataRepository;
import com.app.coronatracker.ui.dashboard.model.Country;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<Country> mutableLiveData;
    private DataRepository dataRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        dataRepository = DataRepository.getInstance();
        mutableLiveData = dataRepository.getCountriesData();
    }

    public LiveData<Country> getDataRepository() {
        return mutableLiveData;
    }
}