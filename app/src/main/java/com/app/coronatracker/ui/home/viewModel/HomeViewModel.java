package com.app.coronatracker.ui.home.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.coronatracker.ui.Repository.GlobalDataRepository;
import com.app.coronatracker.ui.Repository.GlobalDataService;
import com.app.coronatracker.ui.Repository.IndianStatesDataRepository;
import com.app.coronatracker.ui.Repository.IndianStatesDataService;
import com.app.coronatracker.ui.api.GlobalData.GlobalDataWebService;
import com.app.coronatracker.ui.api.IndianData.IndianDataWebService;
import com.app.coronatracker.ui.home.model.Dashboard;
import com.app.coronatracker.ui.home.model.State;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Dashboard> mutableLiveData;
    private MutableLiveData<ArrayList<State>> indianStatesData;

    //Repositories
    private GlobalDataRepository globalDataRepository;
    private IndianStatesDataRepository indianDataRepository;

    public void init(){

        if (mutableLiveData != null){
            return;
        }

        // Setting up repositories
        globalDataRepository = getGlobalDataRepository();
        indianDataRepository = getIndianStatesDataRepository();

        // Prepare live data
        setupLiveData();
    }

    private GlobalDataRepository getGlobalDataRepository(){
        return new GlobalDataService( GlobalDataWebService.getInstance());
    }

    private IndianStatesDataRepository getIndianStatesDataRepository(){
        return new IndianStatesDataService(IndianDataWebService.getInstance() );
    }

    private void setupLiveData(){
        mutableLiveData = globalDataRepository.getGlobalData();
        indianStatesData = indianDataRepository.getStates();
    }

    public LiveData<Dashboard> getDashBoardData() {
        return mutableLiveData;
    }
}