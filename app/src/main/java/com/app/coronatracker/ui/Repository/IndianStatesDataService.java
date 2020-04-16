package com.app.coronatracker.ui.Repository;

import androidx.lifecycle.MutableLiveData;

import com.app.coronatracker.ui.api.IndianData.IndianDataWebInterface;
import com.app.coronatracker.ui.api.utils.APIError;
import com.app.coronatracker.ui.api.utils.CTError;
import com.app.coronatracker.ui.home.model.State;

import java.util.ArrayList;

public class IndianStatesDataService implements IndianStatesDataRepository {

    IndianDataWebInterface webservice;

    public IndianStatesDataService(IndianDataWebInterface webservice){
        this.webservice = webservice;
    }

    @Override
    public MutableLiveData<ArrayList<State>> getStates() {
        return webservice.getStateWise();
    }

    @Override
    public MutableLiveData<CTError> onError() {
        return webservice.onError();
    }
}
