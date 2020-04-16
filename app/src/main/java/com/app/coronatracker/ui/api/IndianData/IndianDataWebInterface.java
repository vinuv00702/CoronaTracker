package com.app.coronatracker.ui.api.IndianData;

import androidx.lifecycle.MutableLiveData;

import com.app.coronatracker.ui.api.utils.APIError;
import com.app.coronatracker.ui.api.utils.CTError;
import com.app.coronatracker.ui.home.model.State;

import java.util.ArrayList;

public interface IndianDataWebInterface {
    public MutableLiveData<ArrayList<State>> getStateWise();
    public MutableLiveData<CTError> onError();
}
