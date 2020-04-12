package com.app.coronatracker.ui.dashboard.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.coronatracker.ui.Repository.GlobalDataRepository;
import com.app.coronatracker.ui.Repository.GlobalDataService;
import com.app.coronatracker.ui.api.GlobalData.GlobalDataWebService;
import com.app.coronatracker.ui.dashboard.model.Country;

import java.util.ArrayList;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Country>> mutableLiveData;
    private GlobalDataRepository globalDataRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        globalDataRepository = new GlobalDataService( GlobalDataWebService.getInstance() );
        mutableLiveData = globalDataRepository.getCountriesData();
    }

    public LiveData<ArrayList<Country>> getGlobalDataRepository() {
        return mutableLiveData;
    }
}