package com.app.coronatracker.ui.api.IndianData;

import com.app.coronatracker.ui.dashboard.model.Country;
import com.app.coronatracker.ui.home.model.Dashboard;
import com.app.coronatracker.ui.home.model.State;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IndianDataApi {

    @GET("getStatewise")
    Call<ArrayList<State>> getStateWise();

}
