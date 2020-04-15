package com.app.coronatracker.ui.api.IndianData;


import androidx.lifecycle.MutableLiveData;

import com.app.coronatracker.ui.api.APIConstant;
import com.app.coronatracker.ui.api.GlobalData.GlobalDataWebService;
import com.app.coronatracker.ui.home.model.Dashboard;
import com.app.coronatracker.ui.home.model.State;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IndianDataWebService implements IndianDataWebInterface {

    IndianDataApi indianDataApi;
    public static  IndianDataWebService indianDataWebService;
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIConstant.coronaTrackerBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public IndianDataWebService(){
        indianDataApi = retrofit.create(IndianDataApi.class);
    }

    // Singleton instance
    public static IndianDataWebService getInstance(){
        if (indianDataWebService == null){
            indianDataWebService = new IndianDataWebService();
        }
        return indianDataWebService;
    }

    @Override
    public MutableLiveData<ArrayList<State>> getStateWise() {

        final MutableLiveData<ArrayList<State>> liveData = new MutableLiveData<>();

        indianDataApi.getStateWise().enqueue(new Callback<ArrayList<State>>() {
            @Override
            public void onResponse(Call<ArrayList<State>> call, Response<ArrayList<State>> response) {
                if(response.isSuccessful()){
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<State>> call, Throwable t) {
                //liveData.setValue(null);
            }
        });

        return liveData;
    }
}
