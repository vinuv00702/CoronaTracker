package com.app.coronatracker.ui.api.IndianData;

import com.app.coronatracker.ui.api.APIConstant;
import com.app.coronatracker.ui.dashboard.model.Country;
import com.app.coronatracker.ui.home.model.Dashboard;
import com.app.coronatracker.ui.home.model.State;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface IndianDataApi {

    @Headers({
           "x-rapidapi-host:"+ APIConstant.CORONATRACKER_HOST,
            "x-rapidapi-key:"+ APIConstant.CORONATRACKER_API_KEY
    })
    @GET("getIndiaStateData")
    Call<IndianDataWebService.Result> getStateWise();

}
