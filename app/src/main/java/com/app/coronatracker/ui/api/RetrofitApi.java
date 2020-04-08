package com.app.coronatracker.ui.api;

import com.app.coronatracker.ui.dashboard.model.Country;
import com.app.coronatracker.ui.home.model.Dashboard;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    @GET("all")
    Call<Dashboard> getDashboardData();

    @GET("countries")
    Call<Country> getCountriesData();
}
