package com.app.coronatracker.ui.api.IndianData;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.app.coronatracker.ui.api.APIConstant;
import com.app.coronatracker.ui.api.utils.APIError;
import com.app.coronatracker.ui.api.utils.CTError;
import com.app.coronatracker.ui.home.model.State;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IndianDataWebService implements IndianDataWebInterface {

    public class Result{
        @SerializedName("key_values")
        @Expose
        private String key_values;

        @SerializedName("total_values")
        @Expose
        private String total_values;

        @SerializedName("state_wise")
        @Expose
        private ArrayList<State> states;

        public String getKey_values() {
            return key_values;
        }

        public void setKey_values(String key_values) {
            this.key_values = key_values;
        }

        public String getTotal_values() {
            return total_values;
        }

        public void setTotal_values(String total_values) {
            this.total_values = total_values;
        }

        public ArrayList<State> getStates() {
            return states;
        }

        public void setStates(ArrayList<State> states) {
            this.states = states;
        }
    }


    IndianDataApi indianDataApi;
    MutableLiveData<CTError> apiError;
    public static  IndianDataWebService indianDataWebService;
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIConstant.coronaTrackerBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public IndianDataWebService(){
        apiError = new MutableLiveData<>();
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


        indianDataApi.getStateWise().enqueue(new Callback<IndianDataWebService.Result>() {
            @Override
            public void onResponse(Call<IndianDataWebService.Result> call, Response<IndianDataWebService.Result> response) {
                if(response.isSuccessful()){
                    ArrayList<State> states =  response.body().getStates();
                    liveData.setValue(states);
                }else {
                    APIError _apiError = new APIError();
                    _apiError.setCode(response.code());
                    _apiError.setMessage(response.message());
                    apiError.setValue(_apiError);
                }
            }

            @Override
            public void onFailure(Call<IndianDataWebService.Result> call, Throwable t) {
                APIError _apiError = new APIError();
                _apiError.setCode(-1000);
                _apiError.setMessage(t.getLocalizedMessage());
                apiError.setValue(_apiError);
            }

        });

        return liveData;
    }

    @Override
    public MutableLiveData<CTError> onError() {
        return apiError;
    }
}
