package com.app.coronatracker.ui.home.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
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


    public class IndianStateModel {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    private MutableLiveData<Dashboard> mutableLiveData;
    private MutableLiveData<ArrayList<IndianStateModel>> indianStatesLiveData;

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
        observerGlobalDataRepository();
        observeIndianStatesRepository();
    }

    private GlobalDataRepository getGlobalDataRepository(){
        return new GlobalDataService( GlobalDataWebService.getInstance());
    }

    private IndianStatesDataRepository getIndianStatesDataRepository(){
        return new IndianStatesDataService(IndianDataWebService.getInstance() );
    }

    private void observerGlobalDataRepository(){
        mutableLiveData = globalDataRepository.getGlobalData();
    }

    private void observeIndianStatesRepository(){
        indianStatesLiveData = new MutableLiveData<ArrayList<IndianStateModel>>();
        ArrayList<IndianStateModel> indianStateModels = new ArrayList<IndianStateModel>();
        indianDataRepository.getStates().observeForever(new Observer<ArrayList<State>>() {
            @Override
            public void onChanged(ArrayList<State> states) {
                for (State state : states)
                {
                    // Extract states name from liveData
                    IndianStateModel _indianStateModelModel = new IndianStateModel();
                    _indianStateModelModel.setName(state.getName());
                    indianStateModels.add(_indianStateModelModel);
                    indianStatesLiveData.setValue(indianStateModels);
                }
            }
        });
    }

    public LiveData<Dashboard> getDashBoardData() {
        return mutableLiveData;
    }

    public LiveData<ArrayList<IndianStateModel>> getStates(){ return indianStatesLiveData; }



}