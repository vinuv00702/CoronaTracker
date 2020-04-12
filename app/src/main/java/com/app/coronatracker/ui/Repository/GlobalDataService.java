package com.app.coronatracker.ui.Repository;

import androidx.lifecycle.MutableLiveData;

import com.app.coronatracker.ui.api.GlobalData.GlobalDataWebInterface;
import com.app.coronatracker.ui.dashboard.model.Country;
import com.app.coronatracker.ui.home.model.Dashboard;

import java.util.ArrayList;

public class GlobalDataService implements GlobalDataRepository {

    //Service class can be configured with localDb like 'Room' or 'RelamDB' ..etc

    GlobalDataWebInterface webService;

    public GlobalDataService( GlobalDataWebInterface webService ){
        this.webService = webService;
    }

    @Override
    public MutableLiveData<Dashboard> getGlobalData() {
        return webService.getGlobalData();
    }

    @Override
    public MutableLiveData<ArrayList<Country>> getCountriesData() {
        return webService.getCountriesData();
    }
}
