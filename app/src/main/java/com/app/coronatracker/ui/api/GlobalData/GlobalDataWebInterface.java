package com.app.coronatracker.ui.api.GlobalData;

import androidx.lifecycle.MutableLiveData;

import com.app.coronatracker.ui.dashboard.model.Country;
import com.app.coronatracker.ui.home.model.Dashboard;

public interface GlobalDataWebInterface{
    public MutableLiveData<Dashboard> getGlobalData();
    public MutableLiveData<Country> getCountriesData();
}
