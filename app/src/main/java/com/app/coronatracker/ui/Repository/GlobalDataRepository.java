package com.app.coronatracker.ui.Repository;

import androidx.lifecycle.MutableLiveData;

import com.app.coronatracker.ui.dashboard.model.Country;
import com.app.coronatracker.ui.home.model.Dashboard;

import java.util.ArrayList;

public interface GlobalDataRepository {
    public MutableLiveData<Dashboard> getGlobalData();
    public MutableLiveData<ArrayList<Country>> getCountriesData();
}
