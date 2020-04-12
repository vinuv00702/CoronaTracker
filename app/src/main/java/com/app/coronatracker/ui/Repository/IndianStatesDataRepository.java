package com.app.coronatracker.ui.Repository;

import androidx.lifecycle.MutableLiveData;

import com.app.coronatracker.ui.home.model.Dashboard;
import com.app.coronatracker.ui.home.model.State;

import java.util.ArrayList;

public interface IndianStatesDataRepository {
    public MutableLiveData<ArrayList<State>> getStates();
}
