package com.app.coronatracker.ui.api;

import androidx.lifecycle.MutableLiveData;

import com.app.coronatracker.ui.dashboard.model.Country;
import com.app.coronatracker.ui.home.model.Dashboard;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {
    private static DataRepository dataRepository;

    public static DataRepository getInstance(){
        if (dataRepository == null){
            dataRepository = new DataRepository();
        }
        return dataRepository;
    }

    private com.app.coronatracker.ui.api.RetrofitApi retrofitApi;

    public DataRepository(){
        retrofitApi = com.app.coronatracker.ui.api.RetrofitService.createService
                (com.app.coronatracker.ui.api.RetrofitApi.class);
    }

    public MutableLiveData<Dashboard> getData(){

        final MutableLiveData<Dashboard> liveData = new MutableLiveData<>();

        retrofitApi.getDashboardData().enqueue(new Callback<Dashboard>() {
            @Override
            public void onResponse(Call<Dashboard> call, Response<Dashboard> response) {

                if (response.isSuccessful()){
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Dashboard> call, Throwable t) {

                liveData.setValue(null);
            }
        });
        return liveData;
    }

    public MutableLiveData<Country> getCountriesData(){

        final MutableLiveData<Country> liveData = new MutableLiveData<>();

        retrofitApi.getCountriesData().enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {

                if (response.isSuccessful()){
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {

                liveData.setValue(null);
            }
        });
        return liveData;
    }

}
