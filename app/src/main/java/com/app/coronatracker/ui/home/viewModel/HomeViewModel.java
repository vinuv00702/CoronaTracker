package com.app.coronatracker.ui.home.viewModel;

import android.util.Log;

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
import com.app.coronatracker.ui.api.utils.CTError;
import com.app.coronatracker.ui.home.model.Dashboard;
import com.app.coronatracker.ui.home.model.State;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {


    public class IndianStateModel {
        private String name;
        private int id = 0;
        private int death = 0;
        private int recovered = 0;
        private int active = 0;
        private int confirmed = 0;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDeath() {
            return death;
        }

        public void setDeath(String death) {
            if (death == null){
                this.death = 0;
            }else{
                this.death = Integer.parseInt(death.trim());
            }

        }

        public int getRecovered() {
            return recovered;
        }

        public void setRecovered(String recovered) {
            if (recovered == null){
                this.recovered = 0;
            }else{
                this.recovered = Integer.parseInt(recovered.trim());
            }
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public int getConfirmed() {
            return confirmed;
        }

        public void setConfirmed(String confirmed) {
            if (confirmed == null){
                this.confirmed = 0;
            }else{
                this.confirmed = Integer.parseInt(confirmed.trim());
            }
        }
    }


    private MutableLiveData<Dashboard> mutableLiveData;
    private MutableLiveData<ArrayList<IndianStateModel>> indianStatesLiveData;
    private MutableLiveData<CTError> error;

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
        observeForErrors();
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
                    _indianStateModelModel.setDeath(state.getDeaths());
                    //_indianStateModelModel.setActive(state.getActive());
                    _indianStateModelModel.setRecovered(state.getRecovered());
                    _indianStateModelModel.setConfirmed(state.getConfirmed());
                    indianStateModels.add(_indianStateModelModel);
                }
                indianStatesLiveData.setValue(indianStateModels);
            }
        });
    }

    private void observeForErrors(){
        indianDataRepository.onError().observeForever(new Observer<CTError>() {
            @Override
            public void onChanged(CTError ctError) {
                error = new MutableLiveData<>();
                Log.e("API Error-->","error-"+ctError.getErrorMessage()+"code-"+ctError.getErrorCode());
                error.setValue(ctError);
            }
        });
    }

    public LiveData<Dashboard> getDashBoardData() {
        return mutableLiveData;
    }

    public LiveData<ArrayList<IndianStateModel>> getStates(){
        return indianStatesLiveData;
    }

}