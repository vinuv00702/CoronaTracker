package com.app.coronatracker.ui.dashboard.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.coronatracker.ui.Repository.GlobalDataRepository;
import com.app.coronatracker.ui.Repository.GlobalDataService;
import com.app.coronatracker.ui.api.GlobalData.GlobalDataWebService;
import com.app.coronatracker.ui.dashboard.model.Country;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<Country> mutableLiveData;
    private GlobalDataRepository globalDataRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        globalDataRepository = new GlobalDataService( GlobalDataWebService.getInstance() );
        mutableLiveData = globalDataRepository.getCountriesData();
    }

    public LiveData<Country> getGlobalDataRepository() {
        return mutableLiveData;
    }
}