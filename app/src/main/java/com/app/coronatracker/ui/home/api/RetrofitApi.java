package com.app.coronatracker.ui.home.api;

import com.app.coronatracker.ui.home.model.ModelPojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    @GET("all")
    Call<ModelPojo> getJsonData();
}
