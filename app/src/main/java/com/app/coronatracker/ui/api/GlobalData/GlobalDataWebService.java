package com.app.coronatracker.ui.api.GlobalData;

import androidx.lifecycle.MutableLiveData;

import com.app.coronatracker.ui.api.APIConstant;
import com.app.coronatracker.ui.dashboard.model.Country;
import com.app.coronatracker.ui.home.model.Dashboard;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GlobalDataWebService implements GlobalDataWebInterface {

    private static GlobalDataWebService dataRepository;
    private GlobalDataApi globalDataApi;
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIConstant.herokuBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public GlobalDataWebService(){
        globalDataApi = retrofit.create(GlobalDataApi.class);
    }

    // Singleton instance
    public static GlobalDataWebService getInstance(){
        if (dataRepository == null){
            dataRepository = new GlobalDataWebService();
        }
        return dataRepository;
    }


    public MutableLiveData<Dashboard> getGlobalData(){

        final MutableLiveData<Dashboard> liveData = new MutableLiveData<>();

        globalDataApi.getDashboardData().enqueue(new Callback<Dashboard>() {
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

        globalDataApi.getCountriesData().enqueue(new Callback<Country>() {
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
